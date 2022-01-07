package pt.codemaster.rest;

import pt.codemaster.adt.analytics.ActivityAnalyticsReport;

import java.util.Collection;

public interface IAnalyticsInternalProvider {
    Collection<ActivityAnalyticsReport> userAnalytics(Long userId, Long activityId);
    Collection<ActivityAnalyticsReport> userActivityData(Long userId, Long activityId);
}
