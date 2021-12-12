package pt.codemaster.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;
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
        ActivityAnalytics analytics = new ActivityAnalytics();
        analytics.setDeliverable(deliverable);
        analytics.setValue(value);
        analytics.setName(name);
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
