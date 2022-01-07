package pt.codemaster.adt.analytics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as=INameValuePair.class)
public class AnalyticsNameValuePair implements INameValuePair {
    private String name;
    private String value;

    public AnalyticsNameValuePair(String name, String value) {
        setName(name);
        setValue(value);
    }

    public AnalyticsNameValuePair() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
