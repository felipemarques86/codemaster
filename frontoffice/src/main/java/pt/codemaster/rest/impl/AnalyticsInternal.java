package pt.codemaster.rest.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.codemaster.adt.analytics.ActivityAnalyticsDto;
import pt.codemaster.rest.IAnalyticsInternalProvider;

import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class AnalyticsInternal implements IAnalyticsInternalProvider {
    @GetMapping(value = "/analytics/activity/{activityId}/user/{userId}/analytics", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Collection<ActivityAnalyticsDto> userAnalytics(Long userId, Long activityId) {
        return null;
    }

    @GetMapping(value = "/analytics/activity/{activityId}/user/{userId}/data", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Collection<ActivityAnalyticsDto> userActivityData(Long userId, Long activityId) {
        return null;
    }

}
