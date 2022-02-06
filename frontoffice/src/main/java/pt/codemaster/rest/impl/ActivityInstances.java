package pt.codemaster.rest.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.codemaster.adt.*;
import pt.codemaster.rest.IActivityRuntime;
import pt.codemaster.services.IActivityService;
import pt.codemaster.validators.adt.ValidationError;
import pt.codemaster.validators.impl.CodeValidator;
import pt.codemaster.validators.impl.DeliverableValidator;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static pt.codemaster.adt.NotificationEvent.NotificationType.*;

@RestController
@CrossOrigin
public class ActivityInstances implements IActivityRuntime {

    @Autowired
    private IActivityService activityService;


    @Override
    @PostMapping(value="/code", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Code> updateCode(@RequestBody Code dto) {
        List<ValidationError> errors = CodeValidator.getInstance().validate(dto);
        if(!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        Code code  = activityService.getCode(dto.getId());
        code.setCode(dto.getCode());
        code.setLanguage(dto.getLanguage());
        code = activityService.saveCode(code);
        code.notifySubscribers(new NotificationEvent(CODE_SAVED, code.getAuthor().getName() + " saved the code"));
        return  ResponseEntity.ok(code);
    }

    @Override
    @PostMapping(value="/code/{codeId}/user/{userId}/comment/{line}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code addComment(@RequestBody Comment comment, @PathVariable("codeId") Long codeId,
                           @PathVariable("userId") String userId, @PathVariable("line") Long line) {
        Code code = activityService.addComment(codeId, userId, line, comment);
        code.notifySubscribers(new NotificationEvent(COMMENT_ADDED, comment.getAuthor().getName() + " comentou o código " + codeId + " linha " + line ));
        return code;
    }

    @Override
    @PostMapping(value="/comment/{commentId}/user/{userId}/reply", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Comment replyComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId,
                                @PathVariable("userId") String userId) {
        comment = activityService.replyComment(userId, commentId, comment);
        comment.notifySubscribers(new NotificationEvent(COMMENT_REPLY, comment.getAuthor().getName() + " respondeu um comentário" ));

        return comment;
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
    @PostMapping(value = "/cea/{id}/deliverable/submit", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public ResponseEntity<Deliverable> submit(@PathVariable("id") Long ceaId, @RequestBody Deliverable deliverable) {
        List<ValidationError> errors = DeliverableValidator.getInstance().validate(deliverable);
        if(!errors.isEmpty()) {
            return new ResponseEntity<>(deliverable, HttpStatus.BAD_REQUEST);
        }
        try {
            deliverable = activityService.submit(ceaId, deliverable);
        } catch(Exception exception) {
            return new ResponseEntity<>(deliverable, HttpStatus.BAD_REQUEST);
        }

        if(deliverable == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        deliverable.notifySubscribers(new NotificationEvent(DELIVERABLE_SUBMITTED, deliverable.getAuthor().getName() + " submeteu o seu código" ));

        return ResponseEntity.ok(deliverable);
    }




}
