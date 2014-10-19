package report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.AccountRepository;
import repository.NoteRepository;
import repository.PrivilegeRepository;
import service.NoteService;
import view.PageNoteView;
import view.PlainNoteView;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nectarius on 18.05.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class NoteReportTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NoteService  noteService;

    public static final String TEMPLATE = "/home/adelfiri/workspaces/harvester/src/main/resources/reports/notes.jrxml";

    private JRDataSource getDataSource() {

        PageNoteView notes = noteService.findAllNoteList("Felix", 0, 5, "asc", "theme");

        List<PlainNoteView> notesList = notes.getContent();

        for(PlainNoteView plainNoteView : notesList){
            System.out.println(plainNoteView.getText());
        }


        return new JRBeanCollectionDataSource(notesList);
    }

    public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRXlsExporter instance
        JRPdfExporter exporter = new JRPdfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    //@Test
    public void emptyTest() throws IOException {
        //System.out.println("Working Directory = " +
       //         System.getProperty("user.dir"));
        //String current = new java.io.File( "." ).getCanonicalPath();
        //System.out.println("Current dir:"+current);

        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for(String font:e.getAvailableFontFamilyNames()) {
            System.out.println(font);
        }
    }



    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        try {
            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("title", "Заметки");

            // 2.  Retrieve template
            InputStream reportStream = new FileInputStream(TEMPLATE);

                    //this.getClass().getResourceAsStream(TEMPLATE);

            //new FileInputStream(TEMPLATE);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, getDataSource());

            JasperExportManager.exportReportToPdfFile(jp, "/home/adelfiri/test.pdf");

            // 6. Create an output byte stream where data will be written
            //ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 7. Export report
            //exportPdf(jp, baos);

            // 8. Write to reponse stream
            //write(token, response, baos);

        } catch (JRException jre) {
            //logger.error("Unable to process download");
            throw new RuntimeException(jre);
        } catch (FileNotFoundException exc) {
            throw new RuntimeException(exc);
        }


    }

}
