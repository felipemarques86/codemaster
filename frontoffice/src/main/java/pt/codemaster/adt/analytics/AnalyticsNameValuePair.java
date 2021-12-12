package pt.codemaster.adt.analytics;

public class AnalyticsNameValuePair {
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
