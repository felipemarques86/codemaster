package pt.codemaster.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.activity.Activity;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface IActivityDefinitions {
    Activity getActivity(Long id);
    ResponseEntity<Activity> saveActivity(Activity activity);
    ResponseEntity<Activity> validateActivity(Activity activity);
    String configSample();
}
