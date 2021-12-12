package pt.codemaster.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.activity.ActivityDeployRequest;
import pt.codemaster.adt.analytics.ActivityAnalyticsDto;
import pt.codemaster.adt.analytics.AnalyticsNameValuePair;
import pt.codemaster.adt.analytics.AnalyticsRequest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin
public class ApiRest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AnalyticsService analyticsService;


    @PostMapping(value="/activity", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    @PostMapping(value="/code", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code updateCode(@RequestBody Code code) {
        return activityService.saveCode(code);
    }

    @PostMapping(value="/code/{codeId}/user/{userId}/comment/{line}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code addComment(@RequestBody Comment comment, @PathVariable("codeId") Long codeId,
                           @PathVariable("userId") String userId, @PathVariable("line") Long line) {
        return activityService.addComment(codeId, userId, line, comment);
    }

    @PostMapping(value="/comment/{commentId}/user/{userId}/reply", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Comment replyComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId,
                             @PathVariable("userId") String userId) {
        return activityService.replyComment(userId, commentId, comment);
    }

    @PostMapping(value="/user", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public EndUser saveEndUser(@RequestBody EndUser endUser) {
        return activityService.saveEndUser(endUser);
    }

    @GetMapping(value="/code/{id}", produces = APPLICATION_JSON)
    public Code getCode(@PathVariable("id") Long id) {
        return activityService.getCode(id);
    }

    @GetMapping(value="/activity/{id}", produces = APPLICATION_JSON)
    public Activity getActivity(@PathVariable("id") Long id) {
        return activityService.getActivity(id);
    }

    @GetMapping(value = "/cea/{id}/{userId}", produces = APPLICATION_JSON)
    public ActivityInstance createCea(@PathVariable("id") Long id, @PathVariable("userId") String userId) {
        return activityService.createInstance(id, userId, null);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    /*        return "[\n" +
                "  {\n" +
                "    \"inveniraStdID\": 1001,\n" +
                "    \"quantAnalytics\": [\n" +
                "      {\n" +
                "        \"name\": \"Acedeu à atividade\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Download documento 1\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Evolução pela atividade (%)\",\n" +
                "        \"value\": \"33.3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"qualAnalytics\": [\n" +
                "      {\n" +
                "        \"Student activity profile\": \"https://ActivityProvider/?APAnID=11111111\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Actitivy Heat Map\": \"https://ActivityProvider/?APAnID=21111111\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"inveniraStdID\": 1002,\n" +
                "    \"quantAnalytics\": [\n" +
                "      {\n" +
                "        \"name\": \"Acedeu à atividade\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Download documento 1\",\n" +
                "        \"value\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Evolução pela atividade (%)\",\n" +
                "        \"value\": \"10.0\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"qualAnalytics\": [\n" +
                "      {\n" +
                "        \"Student activity profile\": \"https://ActivityProvider/?APAnID=11111112\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Actitivy Heat Map\": \"https://ActivityProvider/?APAnID=21111112\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";*/
    @PostMapping(value = "/analytics.json", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Collection<ActivityAnalyticsDto> analytics(@RequestBody AnalyticsRequest request) {

        Activity activity = activityService.getActivity(Long.parseLong(request.getActivityID()));
        if( activity != null) {
            List<ActivityAnalytics> analytics = analyticsService.getAnalytics(activity);
            List<ActivityInstance<Activity>> instances = activityService.getInstances(activity.getId());

            Map<Long, ActivityAnalyticsDto> data = new HashMap<>();
            for(ActivityInstance<Activity> instance : instances) {
                for(Deliverable deliverable : instance.getDeliverable()) {
                    Long id = Long.parseLong(deliverable.getAuthor().getId());
                    ActivityAnalyticsDto dto = new ActivityAnalyticsDto();
                    dto.setInveniraStdID(deliverable.getAuthor().getId());
                    dto.getQuantAnalytics().add(new AnalyticsNameValuePair("Submetido", Boolean.toString(deliverable.isSubmitted())));
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

    @GetMapping(value = "/config")
    @ResponseBody
    public ModelAndView config() {
        return new ModelAndView("redirect:" + "#/config.html");
    }

    @PostMapping(value = "/deployment", consumes = APPLICATION_JSON)
    public RedirectView deployment(@RequestBody ActivityDeployRequest deployRequest) {
        ActivityInstance instance = this.activityService.createInstance(Long.parseLong(deployRequest.getActivityID()), deployRequest.getInveniRAstdID(), deployRequest.getJson_params());
        System.out.println("#/activity/" + instance.getId() + "/user/" + deployRequest.getInveniRAstdID() + "/index.html");
        return new RedirectView("#/activity/" + instance.getId() + "/user/" + deployRequest.getInveniRAstdID() + "/index.html");
    }

    @GetMapping(value = "/cea/{id}", produces = APPLICATION_JSON)
    public ActivityInstance getCea(@PathVariable("id") Long id) {
        return activityService.getInstance(id);
    }

    @PostMapping(value = "/deliverable/submit", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public Deliverable submit(@RequestBody Deliverable deliverable) {
        return activityService.submit(deliverable);
    }

    @GetMapping(value = "/config-sample.json", produces = APPLICATION_JSON)
    @ResponseBody
    public String configSample() {
        return "{\n" +
                "\t\"name\":\"\",\n" +
                "\t\"description\":\"\",\n" +
                "\t\"activityUnitTestList\":[],\n" +
                "\t\"solution\":[],\n" +
                "\t\"bibliographicReferenceList\":[]\n" +
                "}";
    }


}
