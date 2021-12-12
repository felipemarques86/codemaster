package pt.codemaster.rest.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.ActivityAnalyticsDto;
import pt.codemaster.adt.analytics.AnalyticsNameValuePair;
import pt.codemaster.adt.analytics.AnalyticsRequest;
import pt.codemaster.rest.IAnalyticsExternalProvider;
import pt.codemaster.rest.IAnalyticsRecorder;
import pt.codemaster.services.IActivityDefinitionService;
import pt.codemaster.services.IActivityService;
import pt.codemaster.services.IAnalyticsService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Collection<ActivityAnalyticsDto> analytics(@RequestBody AnalyticsRequest request) {

        Activity activity = activityDefinitionService.getActivity(Long.parseLong(request.getActivityID()));
        if( activity != null) {
            List<ActivityAnalytics> analytics = analyticsService.getAnalytics(activity);
            List<ActivityInstance> instances = activityService.getInstances(activity.getId());

            Map<Long, ActivityAnalyticsDto> data = new HashMap<>();
            for(ActivityInstance instance : instances) {
                for(Deliverable deliverable : instance.getDeliverable()) {
                    Long id = Long.parseLong(deliverable.getAuthor().getId());
                    ActivityAnalyticsDto dto = new ActivityAnalyticsDto();
                    dto.setInveniraStdID(deliverable.getAuthor().getId());
                    dto.getQuantAnalytics().add(new AnalyticsNameValuePair("Submetido", Boolean.toString(deliverable.isSubmitted())));
                    dto.getQuantAnalytics()
                            .add(new AnalyticsNameValuePair("Linhas de Código",
                                    deliverable.getCode().getCode() != null ?
                                            Integer.toString(
                                                    StringUtils.countMatches(deliverable.getCode().getCode(), "\n"))
                                            : "0"));


                    dto.getQuantAnalytics()
                            .add(new AnalyticsNameValuePair("Comentários Feitos",
                                    Integer.toString(instance.getDeliverable()
                                            .stream()
                                            .flatMap( deliverable1 ->
                                                    Stream.concat(
                                                            deliverable1.getCode()
                                                                    .getCommentList()
                                                                    .stream()
                                                                    .filter( comment -> comment.getAuthor()
                                                                            .getId()
                                                                            .equals(deliverable.getAuthor().getId())),

                                                            deliverable1.getCode()
                                                                    .getCommentList()
                                                                    .stream()
                                                                    .flatMap( comment -> comment.getReplies()
                                                                            .stream()
                                                                            .filter( c -> c.getAuthor()
                                                                                    .getId()
                                                                                    .equals(deliverable
                                                                                            .getAuthor().getId()))))

                                            ).collect(Collectors.toList()).size())));
                    data.put(id, dto);
                }
            }

            for(ActivityAnalytics a : analytics) {
                Long id = Long.parseLong(a.getDeliverable().getAuthor().getId());
                ActivityAnalyticsDto dto =  data.get(id);
                dto.getQuantAnalytics().add(a);
            }

            for(Long id : data.keySet()) {
                data.get(id).getQualAnalytics().add(new AnalyticsNameValuePair("Student activity profile", "http://cea-develop.herokuapp.com/v1/api/#/activity/"+activity.getId()+"/user/" + id + "/analytics.html"));
                data.get(id).getQualAnalytics().add(new AnalyticsNameValuePair("Activity Data", "http://cea-develop.herokuapp.com/v1/api/#/activity/"+activity.getId()+"/user/" + id + "/activityData.html"));
            }

            return data.values();
        }
        return null;
    }



    @PostMapping(value = "/analytics/activity/{activityId}/user/{userId}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ActivityAnalytics save(String name, String value, Long deliverableId) {
        return analyticsService.save(name, value, activityService.getDeliverable(deliverableId));
    }

}
