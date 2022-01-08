package pt.codemaster.services;

import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.EndUser;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.List;

public interface IAnalyticsService {
    ActivityAnalytics save(String name, String value, Deliverable deliverable);
    List<ActivityAnalytics> getAnalytics(Activity activity);

    List<ActivityAnalytics> getAnalytics(EndUser user);

    Collection<ActivityUserAnalytics> getAnalyticsReport(Activity activity);

    Collection<ActivityUserAnalytics> getAnalyticsReport(EndUser user);
}
