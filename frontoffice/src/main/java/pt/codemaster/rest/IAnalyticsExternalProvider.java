package pt.codemaster.rest;

import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.analytics.ActivityAnalyticsDto;
import pt.codemaster.adt.analytics.AnalyticsRequest;

import java.util.Collection;

public interface IAnalyticsExternalProvider {
    Collection<ActivityAnalyticsDto> analytics(@RequestBody AnalyticsRequest request);
}
