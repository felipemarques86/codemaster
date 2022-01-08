package pt.codemaster.adt.analytics.reports;

import pt.codemaster.adt.analytics.ActivityUserAnalytics;
import pt.codemaster.adt.analytics.INameValuePair;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class HtmlReport extends BaseReport {

    @Override
    public byte[] getBinary() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append(getName());
        sb.append("</head><body>");
        sb.append("<h1>"); sb.append(getName()); sb.append("</h1>");
        sb.append("<h2>"); sb.append(new SimpleDateFormat("dd-MM-yy HH:mm").format(getDate())); sb.append("</h2>");
        sb.append("</hr>");
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<th>"); sb.append("InveniraStdID"); sb.append("</th>");
        sb.append("<th>"); sb.append("Tipo");  sb.append("</th>");
        sb.append("<th>"); sb.append("Nome");  sb.append("</th>");
        sb.append("<th>"); sb.append("Valor");  sb.append("</th>");
        sb.append("</tr>\r\n");

        for(ActivityUserAnalytics row : getAnalytics())
        {
            for(INameValuePair val : row.getQualAnalytics()) {
                sb.append("<tr><td>");
                sb.append(row.getInveniraStdID());
                sb.append("</td><td>");
                sb.append("qual");
                sb.append("</td><td>");
                sb.append(val.getName());
                sb.append("</td><td>");
                sb.append(val.getValue());
                sb.append("</td>");
                sb.append("</tr>\r\n");
            }

            for(INameValuePair val : row.getQuantAnalytics()) {
                sb.append("<tr><td>");
                sb.append(row.getInveniraStdID());
                sb.append("</td><td>");
                sb.append("quant");
                sb.append("</td><td>");
                sb.append(val.getName());
                sb.append("</td><td>");
                sb.append(val.getValue());
                sb.append("</td>");
                sb.append("</tr>\r\n");
            }

        }
        sb.append("</table>\r\n");
        sb.append("</body>\r\n");
        sb.append("</html>\r\n");
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String getFileExtension() {
        return "html";
    }

}
