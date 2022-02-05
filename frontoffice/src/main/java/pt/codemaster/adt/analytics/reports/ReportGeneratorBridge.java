package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;

import java.util.Collection;
import java.util.Date;

public class ReportGeneratorBridge implements IReportGenerator {
    private final Report report;


    public ReportGeneratorBridge(Report report) {
        this.report = report;
    }

    @Override
    public byte[] generate(Collection<ActivityUserAnalytics> analyticsCollection) throws Exception {
        report.setAnalytics(analyticsCollection);
        return report.getBinary();
    }

    @Override
    public String getName() {
        return report.getName();
    }

    @Override
    public void setName(String name) {
        report.setName(name);
    }

    @Override
    public Date getDate() {
        return report.getDate();
    }

    @Override
    public void setDate(Date date) {
        report.setDate(date);
    }
}
