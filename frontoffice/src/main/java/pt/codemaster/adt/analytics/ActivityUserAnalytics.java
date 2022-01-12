package pt.codemaster.adt.analytics;

import java.util.ArrayList;
import java.util.List;

public class ActivityUserAnalytics implements IActivityUserAnalytics {
    private String inveniraStdID;
    private List<INameValuePair> quantAnalytics = new ArrayList<>();
    private List<INameValuePair> qualAnalytics = new ArrayList<>();

    @Override
    public String getInveniraStdID() {
        return inveniraStdID;
    }
    @Override
    public void setInveniraStdID(String inveniraStdID) {
        this.inveniraStdID = inveniraStdID;
    }
    @Override
    public List<INameValuePair> getQuantAnalytics() {
        return quantAnalytics;
    }
    @Override
    public void setQuantAnalytics(List<INameValuePair> quantAnalytics) {
        this.quantAnalytics = quantAnalytics;
    }
    @Override
    public List<INameValuePair> getQualAnalytics() {
        return qualAnalytics;
    }
    @Override
    public void setQualAnalytics(List<INameValuePair> qualAnalytics) {
        this.qualAnalytics = qualAnalytics;
    }
}
