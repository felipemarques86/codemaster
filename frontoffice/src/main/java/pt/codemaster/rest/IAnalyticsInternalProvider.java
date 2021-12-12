package pt.codemaster.rest;

import pt.codemaster.adt.analytics.ActivityAnalyticsDto;

import java.util.Collection;

public interface IAnalyticsInternalProvider {
    Collection<ActivityAnalyticsDto> userAnalytics(Long userId, Long activityId);
    Collection<ActivityAnalyticsDto> userActivityData(Long userId, Long activityId);
}
