package pt.codemaster.rest;

import org.springframework.http.ResponseEntity;
import pt.codemaster.adt.activity.Activity;

public interface IActivityDefinitionProvider {
    Activity getActivity(Long id);
    ResponseEntity<Activity> saveActivity(Activity activity);
    ResponseEntity<Activity> validateActivity(Activity activity);
    String configSample();
}
