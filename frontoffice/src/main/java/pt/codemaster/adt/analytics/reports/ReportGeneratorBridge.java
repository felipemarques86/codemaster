package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.Date;

public class ReportGeneratorBridge {
    private Report report;


    public ReportGeneratorBridge(Report report) {
        this.report = report;
    }

    public byte[] generate(Collection<ActivityUserAnalytics> analyticsCollection) throws Exception {
        report.setAnalytics(analyticsCollection);
        return report.getBinary();
    }

    public String getName() {
        return report.getName();
    }

    public void setName(String name) {
        report.setName(name);
    }

    public Date getDate() {
        return report.getDate();
    }

    public void setDate(Date date) {
        report.setDate(date);
    }
}
