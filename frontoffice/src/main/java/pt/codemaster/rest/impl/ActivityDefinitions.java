package pt.codemaster.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.rest.IActivityDefinitionProvider;
import pt.codemaster.services.IActivityDefinitionService;
import pt.codemaster.validators.adt.ValidationError;
import pt.codemaster.validators.impl.ActivityValidator;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class ActivityDefinitions implements IActivityDefinitionProvider {

    @Autowired
    private IActivityDefinitionService activityDefinitionService;

    @Override
    @GetMapping(value="/activity/{id}", produces = APPLICATION_JSON)
    public Activity getActivity(@PathVariable("id") Long id) {
        return activityDefinitionService.getActivity(id);
    }

    @Override
    @PostMapping(value="/activity", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Activity> saveActivity(@RequestBody Activity activity) {
        List<ValidationError> errors = ActivityValidator.getInstance().validate(activity);
        if(!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(activityDefinitionService.saveActivity(activity));
    }

    @Override
    @PostMapping(value="/activity/validate", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Activity> validateActivity(@RequestBody Activity activity) {
        List<ValidationError> errors = ActivityValidator.getInstance().validate(activity);
        if(!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(activity);
    }

    @GetMapping(value = "/config-sample.json", produces = APPLICATION_JSON)
    @ResponseBody
    public String configSample() {
        return "{\n" +
                "\t\"name\":\"\",\n" +
                "\t\"description\":\"\",\n" +
                "\t\"activityUnitTestList\":[],\n" +
                "\t\"solution\":[],\n" +
                "\t\"bibliographicReferenceList\":[]\n" +
                "}";
    }

}
