package pt.codemaster.rest;

import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.analytics.AnalyticsRequest;

public interface IAnalyticsExternalProvider {
    byte[] analyticsJson(@RequestBody AnalyticsRequest request) throws Exception;

}
