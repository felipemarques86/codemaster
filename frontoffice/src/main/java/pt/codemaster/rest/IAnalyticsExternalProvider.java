package pt.codemaster.rest;

import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.analytics.ActivityAnalyticsReport;
import pt.codemaster.adt.analytics.AnalyticsRequest;

import java.util.Collection;

public interface IAnalyticsExternalProvider {
    Collection<ActivityAnalyticsReport> analytics(@RequestBody AnalyticsRequest request);
}
