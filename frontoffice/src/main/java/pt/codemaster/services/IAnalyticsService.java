package pt.codemaster.services;

import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.ActivityAnalyticsReport;

import java.util.Collection;
import java.util.List;

public interface IAnalyticsService {
    ActivityAnalytics save(String name, String value, Deliverable deliverable);
    List<ActivityAnalytics> getAnalytics(Activity activity);
    Collection<ActivityAnalyticsReport> getAnalyticsReport(Activity activity);
}
