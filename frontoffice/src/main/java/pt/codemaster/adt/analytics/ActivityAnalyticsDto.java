package pt.codemaster.adt.analytics;

import java.util.ArrayList;
import java.util.List;

public class ActivityAnalyticsDto {
    private String inveniraStdID;
    private List<AnalyticsNameValuePair> quantAnalytics = new ArrayList<>();
    private List<AnalyticsNameValuePair> qualAnalytics = new ArrayList<>();

    public String getInveniraStdID() {
        return inveniraStdID;
    }

    public void setInveniraStdID(String inveniraStdID) {
        this.inveniraStdID = inveniraStdID;
    }

    public List<AnalyticsNameValuePair> getQuantAnalytics() {
        return quantAnalytics;
    }

    public void setQuantAnalytics(List<AnalyticsNameValuePair> quantAnalytics) {
        this.quantAnalytics = quantAnalytics;
    }

    public List<AnalyticsNameValuePair> getQualAnalytics() {
        return qualAnalytics;
    }

    public void setQualAnalytics(List<AnalyticsNameValuePair> qualAnalytics) {
        this.qualAnalytics = qualAnalytics;
    }
}
