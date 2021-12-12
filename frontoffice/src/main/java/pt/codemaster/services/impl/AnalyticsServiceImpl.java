package pt.codemaster.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.repositories.ActivityAnalyticsRepository;
import pt.codemaster.services.IAnalyticsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements IAnalyticsService {

    @Autowired
    private ActivityAnalyticsRepository activityAnalyticsRepository;

    @Override
    public ActivityAnalytics save(String name, String value, Deliverable deliverable) {

        ActivityAnalytics analytics = activityAnalyticsRepository.findAll()
                .stream()
                .filter( an -> an.getDeliverable().getId() == deliverable.getId() && an.getName().equals(name))
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
}
