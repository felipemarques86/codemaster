package pt.codemaster.adt.analytics;

import java.util.ArrayList;
import java.util.List;

public class ActivityAnalyticsReport {
    private String inveniraStdID;
    private List<INameValuePair> quantAnalytics = new ArrayList<>();
    private List<INameValuePair> qualAnalytics = new ArrayList<>();

    public String getInveniraStdID() {
        return inveniraStdID;
    }

    public void setInveniraStdID(String inveniraStdID) {
        this.inveniraStdID = inveniraStdID;
    }

    public List<INameValuePair> getQuantAnalytics() {
        return quantAnalytics;
    }

    public void setQuantAnalytics(List<INameValuePair> quantAnalytics) {
        this.quantAnalytics = quantAnalytics;
    }

    public List<INameValuePair> getQualAnalytics() {
        return qualAnalytics;
    }

    public void setQualAnalytics(List<INameValuePair> qualAnalytics) {
        this.qualAnalytics = qualAnalytics;
    }
}
