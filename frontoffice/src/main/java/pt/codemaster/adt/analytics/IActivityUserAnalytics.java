package pt.codemaster.adt.analytics;

import java.util.List;

public interface IActivityUserAnalytics {
    String getInveniraStdID();

    void setInveniraStdID(String inveniraStdID);

    List<INameValuePair> getQuantAnalytics();

    void setQuantAnalytics(List<INameValuePair> quantAnalytics);

    List<INameValuePair> getQualAnalytics();

    void setQualAnalytics(List<INameValuePair> qualAnalytics);
}
