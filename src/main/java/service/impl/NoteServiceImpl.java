package service.impl;

import entity.Account;
import entity.Note;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.NoteRepository;
import service.NoteService;
import service.report.ExporterService;
import view.PageNoteView;
import view.PlainNoteView;
import viewmapper.PlainNoteViewMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */

@Service
public class NoteServiceImpl implements NoteService {

    static final String NOTES_TEMPLATE = "/reports/notes.jrxml";

    @Autowired
    private PlainNoteViewMapper plainNoteViewMapper;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExporterService exporter;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class.getName());

    private List<PlainNoteView> findAllNoteList(String author){
        Account account = accountRepository.findByLogin(author);
        return plainNoteViewMapper.createList(noteRepository.findAll());
    }


    @Override
    public PageNoteView findAllNoteList(String author, int pageNumber, int pageSize, String direction, String column) {

        Account account = accountRepository.findByLogin(author);

        Sort.Direction direction_ = Sort.Direction.fromString(direction);

        PageRequest request = new PageRequest(pageNumber, pageSize, direction_, column);

        Page<Note> noteList = noteRepository.findAllByAccountId(account.getId(), request);

        return new PageNoteView(noteList.getTotalPages(), plainNoteViewMapper.createList(noteList.getContent()));
    }

    @Override
    public PlainNoteView saveOrUpdateNote(PlainNoteView view, String authorName) {
        Note note;
        Date date = new Date();
        if (view.getId() == null) {
            note = new Note();
            plainNoteViewMapper.copyFrom(view, note);
            note.setCreateTime(date);
            note.setLastUpdateTime(date);
            Account account = accountRepository.findByLogin(authorName);
            note.setAuthor(account);
        } else {
            note = noteRepository.findOne(view.getId());
            view.setCreateTime(note.getCreateTime());
            plainNoteViewMapper.copyFrom(view, note);
            note.setLastUpdateTime(date);
        }

        note = noteRepository.save(note);

        LOGGER.info("Saved note: id {} name {}", note.getId(), note.getTheme());

        return plainNoteViewMapper.create(note);
    }

    @Override
    public void deleteNote(Long noteId) {
        noteRepository.delete(noteId);
        LOGGER.info("Deleted note: id {}", noteId);
    }

    @Override
    public PlainNoteView findOne(Long noteId) {
        return plainNoteViewMapper.create(noteRepository.findOne(noteId));
    }

    public void download(String author, String type, String token, HttpServletResponse response) {

        try {
            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("Title", "User Report");

            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(NOTES_TEMPLATE);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            List<PlainNoteView> notes = findAllNoteList(author);

            JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(notes));

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 7. Export report
            exporter.export(type, jp, response, baos);

            // 8. Write to reponse stream
            write(token, response, baos);

        } catch (JRException jre) {
            //logger.error("Unable to process download");
            throw new RuntimeException(jre);
        }
    }

    /**
     * Writes the report to the output stream
     */
    private void write(String token, HttpServletResponse response,
                       ByteArrayOutputStream baos) {

        try {
            // logger.debug(baos.size());

            // Retrieve output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();

            // Remove download token
            //tokenService.remove(token);

        } catch (Exception e) {
            //logger.error("Unable to write report to the output stream");
            throw new RuntimeException(e);
        }
    }
}
