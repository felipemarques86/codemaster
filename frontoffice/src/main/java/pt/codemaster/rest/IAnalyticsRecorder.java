package pt.codemaster.rest;

import pt.codemaster.adt.analytics.ActivityAnalytics;

public interface IAnalyticsRecorder {
    ActivityAnalytics save(String name, String value, Long deliverableId);
}