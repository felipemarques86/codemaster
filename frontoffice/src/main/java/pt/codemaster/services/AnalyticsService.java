package pt.codemaster.services;

import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;

import java.util.List;

public interface AnalyticsService {
    ActivityAnalytics save(String name, String value, Deliverable deliverable);
    List<ActivityAnalytics> getAnalytics(Activity activity);
}
