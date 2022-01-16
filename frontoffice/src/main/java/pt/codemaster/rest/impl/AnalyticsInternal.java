package pt.codemaster.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityUserAnalytics;
import pt.codemaster.adt.analytics.AnalyticsRequest;
import pt.codemaster.adt.analytics.reports.*;
import pt.codemaster.rest.IAnalyticsInternalProvider;
import pt.codemaster.services.IActivityDefinitionService;
import pt.codemaster.services.IAnalyticsService;

import java.util.Collection;
import java.util.Date;

import static javax.ws.rs.core.MediaType.*;

@RestController
@CrossOrigin
public class AnalyticsInternal implements IAnalyticsInternalProvider {

    @Autowired
    private IActivityDefinitionService activityDefinitionService;

    @Autowired
    private IAnalyticsService analyticsService;

    @Override
    @PostMapping(value = "/analytics.pdf", consumes = APPLICATION_JSON, produces = APPLICATION_OCTET_STREAM)
    public byte[] analyticsPdf(@RequestBody AnalyticsRequest request) throws Exception {
        Activity activity = activityDefinitionService.getActivity(Long.parseLong(request.getActivityID()));
        Collection<ActivityUserAnalytics> analyticsReport = analyticsService.getAnalyticsReport(activity);
        IReportGenerator generator = new PdfReportGeneratorBridge(new HtmlReport());
        generator.setName("Relatório da atividade - versão PDF");
        generator.setDate(new Date());
        return generator.generate(analyticsReport);
    }

    @Override
    @PostMapping(value = "/analytics.csv", consumes = APPLICATION_JSON, produces = APPLICATION_OCTET_STREAM)
    public byte[] analyticsCsv(@RequestBody AnalyticsRequest request) throws Exception {
        Activity activity = activityDefinitionService.getActivity(Long.parseLong(request.getActivityID()));
        Collection<ActivityUserAnalytics> analyticsReport = analyticsService.getAnalyticsReport(activity);
        IReportGenerator generator = new ReportGeneratorBridge(new CsvReport());
        generator.setName("Relatório da atividade - versão CSV/Excel");
        generator.setDate(new Date());
        return generator.generate(analyticsReport);
    }

    @Override
    @PostMapping(value = "/analytics.html", consumes = APPLICATION_JSON, produces = APPLICATION_XHTML_XML)
    public byte[] analyticsHtml(@RequestBody AnalyticsRequest request) throws Exception {
        Activity activity = activityDefinitionService.getActivity(Long.parseLong(request.getActivityID()));
        Collection<ActivityUserAnalytics> analyticsReport = analyticsService.getAnalyticsReport(activity);
        IReportGenerator generator = new ReportGeneratorBridge(new HtmlReport());
        generator.setName("Relatório da atividade - versão web");
        generator.setDate(new Date());
        return generator.generate(analyticsReport);
    }

}
