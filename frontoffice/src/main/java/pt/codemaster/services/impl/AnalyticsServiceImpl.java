package pt.codemaster.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.ActivityAnalyticsReport;
import pt.codemaster.adt.analytics.AnalyticsNameValuePair;
import pt.codemaster.repositories.ActivityAnalyticsRepository;
import pt.codemaster.services.IActivityService;
import pt.codemaster.services.IAnalyticsService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnalyticsServiceImpl implements IAnalyticsService {

    @Autowired
    private ActivityAnalyticsRepository activityAnalyticsRepository;

    @Autowired
    private IActivityService activityService;

    @Override
    public ActivityAnalytics save(String name, String value, Deliverable deliverable) {

        ActivityAnalytics analytics = activityAnalyticsRepository.findAll()
                .stream()
                .filter( an -> an.getDeliverable() != null && an.getDeliverable().getId() == deliverable.getId() && an.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(analytics == null) {
            analytics = new ActivityAnalytics();
            analytics.setDeliverable(deliverable);
            analytics.setName(name);
        }
        analytics.setValue(value);
        return activityAnalyticsRepository.save(analytics);
    }

    @Override
    public List<ActivityAnalytics> getAnalytics(Activity activity) {
        return activityAnalyticsRepository.findAll()
                .stream()
                .filter( a -> a.getDeliverable().getActivityInstance().getActivity().getId().equals(activity.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ActivityAnalyticsReport> getAnalyticsReport(Activity activity) {
        if( activity != null) {
            List<ActivityAnalytics> analytics = getAnalytics(activity);
            List<ActivityInstance> instances = activityService.getInstances(activity.getId());

            Map<Long, ActivityAnalyticsReport> data = new HashMap<>();
            for(ActivityInstance instance : instances) {
                for(Deliverable deliverable : instance.getDeliverable()) {
                    if(deliverable.getAuthor() == null)
                        continue;
                    Long id = Long.parseLong(deliverable.getAuthor().getId());
                    ActivityAnalyticsReport dto = new ActivityAnalyticsReport();
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
                if(a.getDeliverable().getAuthor() != null ) {
                    Long id = Long.parseLong(a.getDeliverable().getAuthor().getId());
                    ActivityAnalyticsReport dto = data.get(id);
                    dto.getQuantAnalytics().add(a);
                }
            }

            for(Long id : data.keySet()) {
                data.get(id).getQualAnalytics().add(new AnalyticsNameValuePair("Student activity profile", "http://cea-develop.herokuapp.com/v1/api/#/activity/"+activity.getId()+"/user/" + id + "/analytics.html"));
                data.get(id).getQualAnalytics().add(new AnalyticsNameValuePair("Activity Data", "http://cea-develop.herokuapp.com/v1/api/#/activity/"+activity.getId()+"/user/" + id + "/activityData.html"));
            }

            return data.values();
        }
        return null;
    }
}
