package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.Date;

public abstract class BaseReport implements Report {

    private String name;
    private Date date;
    private Collection<ActivityUserAnalytics> analytics;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Collection<ActivityUserAnalytics> getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Collection<ActivityUserAnalytics> analytics) {
        this.analytics = analytics;
    }
}
