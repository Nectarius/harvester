package service.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adelfiri on 05.10.14.
 */
@Service
public class ExporterService {

    public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String MEDIA_TYPE_PDF = "application/pdf";
    public static final String MEDIA_TYPE_HTML = "application/html";
    public static final String MEDIA_TYPE_RTF = "application/rtf";
    public static final String MEDIA_TYPE_DOCX = "application/docx";
    public static final String MEDIA_TYPE_CSV= "application/csv";
    public static final String MEDIA_TYPE_TEXT= "application/text";
    public static final String MEDIA_TYPE_JSON= "application/json";
    public static final String MEDIA_TYPE_XML= "application/xml";
    public static final String MEDIA_TYPE_PPTX= "application/pptx";
    public static final String EXTENSION_TYPE_EXCEL = "xls";
    public static final String EXTENSION_TYPE_PDF = "pdf";
    public static final String EXTENSION_TYPE_HTML = "html";
    public static final String EXTENSION_TYPE_RTF = "rtf";
    public static final String EXTENSION_TYPE_DOCX = "docx";
    public static final String EXTENSION_TYPE_CSV = "csv";
    public static final String EXTENSION_TYPE_TEXT = "text";
    public static final String EXTENSION_TYPE_JSON = "json";
    public static final String EXTENSION_TYPE_XML = "xml";
    public static final String EXTENSION_TYPE_PPTX = "pptx";

    public HttpServletResponse export(String type,
                                      JasperPrint jp,
                                      HttpServletResponse response,
                                      ByteArrayOutputStream baos) {

        if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
            // Export to output stream
            exportXls(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.xls";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_EXCEL);
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_TEXT)) {
            // Export to output stream
            exportText(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.txt";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_TEXT);
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_RTF)) {
            // Export to output stream
            exportRTF(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.rtf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_RTF) ;
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_DOCX)) {
            // Export to output stream
            exportDOCX(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.docx";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_DOCX) ;
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_CSV)) {
            // Export to output stream
            exportCSV(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.csv";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_CSV) ;
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
            // Export to output stream
            exportPdf(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.pdf";
            response.setHeader("Content-Disposition", "inline; filename="+ fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_PDF);
            response.setContentLength(baos.size());

            return response;

        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_HTML)) {
            // Export to output stream
            exportHTML(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.html";
            response.setHeader("Content-Disposition", "inline; filename="+ fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_HTML);
            response.setContentLength(baos.size());

            return response;

        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_PPTX)) {
            // Export to output stream
            exportPPTX(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.pptx";
            response.setHeader("Content-Disposition", "inline; filename="+ fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_PPTX);
            response.setContentLength(baos.size());

            return response;

        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_XML)) {
            // Export to output stream
            exportXML(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.xml";
            response.setHeader("Content-Disposition", "inline; filename="+ fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_XML);
            response.setContentLength(baos.size());

            return response;

        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_JSON)) {
            // Export to output stream
            exportJSON(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            String fileName = "Report.txt";
            response.setHeader("Content-Disposition", "inline; filename="+ fileName);

            // Set content type
            response.setContentType(MEDIA_TYPE_JSON);
            response.setContentLength(baos.size());

            return response;

        }

        throw new RuntimeException("No type set for type " + type);
    }

    public void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRXlsExporter instance
        JRXlsExporter exporter = new JRXlsExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
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

    public void exportHTML(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRXlsExporter instance
        HtmlExporter exporter= new HtmlExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        HtmlExporterOutput htmlExporterOutput = new SimpleHtmlExporterOutput(baos);
        exporter.setExporterOutput(htmlExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportRTF(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRRtfExporter exporter= new JRRtfExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        WriterExporterOutput rtfExporterOutput = new  SimpleWriterExporterOutput(baos);

        exporter.setExporterOutput(rtfExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportCSV(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRAbstractCsvExporter exporter= new JRCsvExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        WriterExporterOutput csvExporterOutput = new  SimpleWriterExporterOutput(baos);

        exporter.setExporterOutput(csvExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportDOCX(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRDocxExporter exporter= new JRDocxExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        SimpleOutputStreamExporterOutput rtfExporterOutput = new  SimpleOutputStreamExporterOutput(baos);

        exporter.setExporterOutput(rtfExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportPPTX(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRPptxExporter exporter= new JRPptxExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        SimpleOutputStreamExporterOutput pptxExporterOutput = new  SimpleOutputStreamExporterOutput(baos);

        exporter.setExporterOutput(pptxExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportXML(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRXmlExporter exporter= new JRXmlExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        WriterExporterOutput xmlExporterOutput = new  SimpleWriterExporterOutput(baos);

        exporter.setExporterOutput(xmlExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportJSON(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JsonExporter exporter= new JsonExporter();

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

        WriterExporterOutput jsonExporterOutput = new  SimpleWriterExporterOutput(baos);

        exporter.setExporterOutput(jsonExporterOutput);

        //SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        //configuration.setFlushOutput();
        //exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }



    public void exportText(JasperPrint jp, ByteArrayOutputStream baos) {

        // Create a JRXlsExporter instance
        JRTextExporter exporter= new JRTextExporter();


        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jp);

        // Here we assign the parameters jp and baos to the exporter
        SimpleExporterInput exporterInput = SimpleExporterInput.getInstance(jasperPrintList);


        exporter.setExporterInput(exporterInput);

        WriterExporterOutput textExporterOutput = new  SimpleWriterExporterOutput(baos);



        exporter.setExporterOutput(textExporterOutput);

        SimpleTextReportConfiguration reportConfiguration = new SimpleTextReportConfiguration();

        reportConfiguration.setPageHeightInChars(50);

        reportConfiguration.setPageWidthInChars(80);

        SimpleTextExporterConfiguration configuration = new SimpleTextExporterConfiguration();

        exporter.setConfiguration(reportConfiguration);
        //configuration.setLineSeparator("");
        //configuration.setFlushOutput();
        configuration.setLineSeparator("-");
        configuration.setPageSeparator("/");
        exporter.setConfiguration(configuration);

        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

}
