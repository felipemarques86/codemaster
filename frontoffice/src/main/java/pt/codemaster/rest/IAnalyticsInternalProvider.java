package pt.codemaster.rest;

import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.analytics.AnalyticsRequest;

public interface IAnalyticsInternalProvider {
    byte[] analyticsPdf(@RequestBody AnalyticsRequest request) throws Exception;
    byte[] analyticsCsv(@RequestBody AnalyticsRequest request) throws Exception;
    byte[] analyticsHtml(@RequestBody AnalyticsRequest request) throws Exception;
}
