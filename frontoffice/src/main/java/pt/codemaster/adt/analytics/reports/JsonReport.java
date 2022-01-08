package pt.codemaster.adt.analytics.reports;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReport extends BaseReport{

    @Override
    public byte[] getBinary() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(getAnalytics());
    }

    @Override
    public String getFileExtension() {
        return "json";
    }
}
