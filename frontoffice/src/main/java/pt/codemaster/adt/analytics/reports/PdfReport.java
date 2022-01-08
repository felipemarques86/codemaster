package pt.codemaster.adt.analytics.reports;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfReport extends HtmlReport {

    @Override
    public byte[] getBinary() throws Exception {
        byte [] data = super.getBinary();
        System.out.println(new String(data));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(false);
        DocumentBuilder dBuilder = factory.newDocumentBuilder();


        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.toStream(os);
            builder.withW3cDocument(dBuilder.parse(new ByteArrayInputStream(data)), "/");
            builder.run();
            data = os.toByteArray();
        }
        return data;
    }

    @Override
    public String getFileExtension() {
        return "pdf";
    }

}
