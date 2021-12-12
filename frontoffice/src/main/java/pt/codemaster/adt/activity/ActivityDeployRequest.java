package pt.codemaster.adt.activity;

import com.fasterxml.jackson.annotation.JsonProperty;

/*{
  "activityID": "This string is the Inven!RA activity ID",
  "Inven!RAstdID": "This string is the student Inven!RA ID",
  "json_params": {
    "s1" : "Setting 1",
    "s2" : "Setting 2",
    "s3" : "Setting 3"
  }
}*/
public class ActivityDeployRequest {
    private String activityID;
    @JsonProperty("Inven!RAstdID")
    private String InveniRAstdID;
    private Activity json_params;

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getInveniRAstdID() {
        return InveniRAstdID;
    }

    public void setInveniRAstdID(String inveniRAstdID) {
        InveniRAstdID = inveniRAstdID;
    }

    public Activity getJson_params() {
        return json_params;
    }

    public void setJson_params(Activity json_params) {
        this.json_params = json_params;
    }
}
