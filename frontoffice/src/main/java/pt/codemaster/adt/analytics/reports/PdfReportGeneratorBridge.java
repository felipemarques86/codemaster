package pt.codemaster.adt.analytics.reports;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class PdfReportGeneratorBridge extends ReportGeneratorBridge {

    public PdfReportGeneratorBridge(Report report) {
        super(report);
    }

    public byte[] convertToPdf(Collection<ActivityUserAnalytics> analyticsCollection) throws Exception {
        byte[] data = super.generate(analyticsCollection);
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

}
