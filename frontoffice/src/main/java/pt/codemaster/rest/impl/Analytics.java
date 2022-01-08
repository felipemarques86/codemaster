package pt.codemaster.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.ActivityUserAnalytics;
import pt.codemaster.adt.analytics.AnalyticsNameValuePair;
import pt.codemaster.adt.analytics.AnalyticsRequest;
import pt.codemaster.adt.analytics.reports.JsonReport;
import pt.codemaster.adt.analytics.reports.ReportGenerator;
import pt.codemaster.rest.IAnalyticsExternalProvider;
import pt.codemaster.rest.IAnalyticsRecorder;
import pt.codemaster.services.IActivityDefinitionService;
import pt.codemaster.services.IActivityService;
import pt.codemaster.services.IAnalyticsService;

import java.util.Collection;
import java.util.Date;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin
public class Analytics implements IAnalyticsExternalProvider, IAnalyticsRecorder {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityDefinitionService activityDefinitionService;

    @Autowired
    private IAnalyticsService analyticsService;

    @PostMapping(value = "/analytics.json", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public byte[] analyticsJson(@RequestBody AnalyticsRequest request) throws Exception {
        Activity activity = activityDefinitionService.getActivity(Long.parseLong(request.getActivityID()));
        Collection<ActivityUserAnalytics> analyticsReport = analyticsService.getAnalyticsReport(activity);
        ReportGenerator generator = new ReportGenerator(new JsonReport());
        generator.setName("Relatório da atividade");
        generator.setDate(new Date());
        return generator.generate(analyticsReport);
    }



    @PostMapping(value = "/analytics/deliverable/{deliverableId}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ActivityAnalytics save(@RequestBody AnalyticsNameValuePair analyticsNameValuePair, @PathVariable("deliverableId") Long deliverableId) {
        return analyticsService.save(analyticsNameValuePair.getName(), analyticsNameValuePair.getValue(), activityService.getDeliverable(deliverableId));
    }

}
