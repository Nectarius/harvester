package service.impl;

import entity.Note;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.NoteRepository;
import service.NoteService;
import view.PageNoteView;
import view.PlainNoteView;
import viewmapper.PlainNoteViewMapper;

import java.util.Date;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private PlainNoteViewMapper plainNoteViewMapper;


    @Autowired
    private NoteRepository noteRepository;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class.getName());


    @Override
    public PageNoteView findAllNoteList(int pageNumber, int pageSize, String direction, String column) {

        Sort.Direction direction_ = Sort.Direction.fromString(direction);

        PageRequest request = new PageRequest(pageNumber, pageSize, direction_, column);

        Page<Note> noteList = noteRepository.findAll(request);

        return new PageNoteView(noteList.getTotalPages(), plainNoteViewMapper.createList(noteList.getContent()));
    }

    @Override
    public PlainNoteView saveOrUpdateNote(PlainNoteView view) {
        Note note;
        Date date = new Date();
        if (view.getId() == null) {
            note = new Note();
            view.setCreateTime(date);
            view.setLastUpdateTime(date);
        } else {
            note = noteRepository.findOne(view.getId());
            view.setLastUpdateTime(date);
        }

        plainNoteViewMapper.copyFrom(view, note);

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
}
