package pt.codemaster.rest.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Code;
import pt.codemaster.adt.Comment;
import pt.codemaster.adt.Deliverable;
import pt.codemaster.rest.IActivityRuntime;
import pt.codemaster.services.IActivityService;
import pt.codemaster.validators.adt.ValidationError;
import pt.codemaster.validators.impl.CodeValidator;
import pt.codemaster.validators.impl.DeliverableValidator;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class ActivityInstances implements IActivityRuntime {

    @Autowired
    private IActivityService activityService;


    @Override
    @PostMapping(value="/code", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Code> updateCode(@RequestBody Code code) {
        List<ValidationError> errors = CodeValidator.getInstance().validate(code);
        if(!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(activityService.saveCode(code));
    }

    @Override
    @PostMapping(value="/code/{codeId}/user/{userId}/comment/{line}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code addComment(@RequestBody Comment comment, @PathVariable("codeId") Long codeId,
                           @PathVariable("userId") String userId, @PathVariable("line") Long line) {
        return activityService.addComment(codeId, userId, line, comment);
    }

    @Override
    @PostMapping(value="/comment/{commentId}/user/{userId}/reply", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Comment replyComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId,
                                @PathVariable("userId") String userId) {
        return activityService.replyComment(userId, commentId, comment);
    }

    @Override
    @GetMapping(value="/code/{id}", produces = APPLICATION_JSON)
    public Code getCode(@PathVariable("id") Long id) {
        return activityService.getCode(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @Override
    @GetMapping(value = "/cea/{id}", produces = APPLICATION_JSON)
    public ActivityInstance getCea(@PathVariable("id") Long id) {
        return activityService.getInstance(id);
    }

    @Override
    @PostMapping(value = "/deliverable/submit", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public ResponseEntity<Deliverable> submit(@RequestBody Deliverable deliverable) {
        List<ValidationError> errors = DeliverableValidator.getInstance().validate(deliverable);
        if(!errors.isEmpty()) {
            return new ResponseEntity<>(deliverable, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(activityService.submit(deliverable));
    }




}
