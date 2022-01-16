package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.Date;

public interface IReportGenerator {
    byte[] generate(Collection<ActivityUserAnalytics> analyticsCollection) throws Exception;

    String getName();

    void setName(String name);

    Date getDate();

    void setDate(Date date);
}
