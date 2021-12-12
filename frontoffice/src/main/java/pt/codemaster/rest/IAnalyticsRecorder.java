package pt.codemaster.rest;

import pt.codemaster.adt.analytics.ActivityAnalytics;
import pt.codemaster.adt.analytics.AnalyticsNameValuePair;

public interface IAnalyticsRecorder {
    ActivityAnalytics save(AnalyticsNameValuePair analyticsNameValuePair, Long deliverableId);
}