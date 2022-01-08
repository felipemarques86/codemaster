package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;
import pt.codemaster.adt.analytics.INameValuePair;

import java.nio.charset.StandardCharsets;

public class CsvReport extends BaseReport {

    @Override
    public byte[] getBinary() {
        StringBuilder sb = new StringBuilder();
        sb.append("inveniraStdID,");
        sb.append("tipo,");
        sb.append("nome,");
        sb.append("valor\r\n");

        for(ActivityUserAnalytics row : getAnalytics())
        {
            for(INameValuePair val : row.getQualAnalytics()) {
                sb.append(row.getInveniraStdID());
                sb.append(",");
                sb.append("qual");
                sb.append(",");
                sb.append(val.getName());
                sb.append(",");
                sb.append(val.getValue());
                sb.append("\r\n");
            }

            for(INameValuePair val : row.getQuantAnalytics()) {
                sb.append(row.getInveniraStdID());
                sb.append(",");
                sb.append("quant");
                sb.append(",");
                sb.append(val.getName());
                sb.append(",");
                sb.append(val.getValue());
                sb.append("\r\n");
            }

        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String getFileExtension() {
        return "csv";
    }

}
