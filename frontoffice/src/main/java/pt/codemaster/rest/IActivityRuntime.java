package pt.codemaster.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Code;
import pt.codemaster.adt.Comment;
import pt.codemaster.adt.Deliverable;

public interface IActivityRuntime {
    ResponseEntity<Code> updateCode(Code code);
    Code addComment(Comment comment, Long codeId, String userId,Long line);
    Comment replyComment(Comment comment, Long commentId, String userId);
    Code getCode(Long id);
    ActivityInstance getCea(Long id);
    ResponseEntity<Deliverable> submit(Long id, @RequestBody Deliverable deliverable);
}
