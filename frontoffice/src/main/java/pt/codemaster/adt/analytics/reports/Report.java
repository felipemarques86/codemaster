package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.Date;

public interface Report {
    String getName();
    void setName(String name);
    Date getDate();
    void setDate(Date date);
    byte[] getBinary() throws Exception;
    String getFileExtension();
    Collection<ActivityUserAnalytics> getAnalytics();
    void setAnalytics(Collection<ActivityUserAnalytics> analytics);
}
