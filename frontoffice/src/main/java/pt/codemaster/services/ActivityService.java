package pt.codemaster.services;

import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.activity.Activity;

public interface ActivityService {
    ActivityInstance<Activity> getInstance(Long id);
    Activity saveActivity(Activity activity);
}
