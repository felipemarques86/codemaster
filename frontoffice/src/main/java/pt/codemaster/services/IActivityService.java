package pt.codemaster.services;

import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Code;
import pt.codemaster.adt.Comment;
import pt.codemaster.adt.Deliverable;

import java.util.List;

public interface IActivityService {
    ActivityInstance getInstance(Long id);
    Code getCode(Long id);
    Code saveCode(Code code);
    Code addComment(Long codeId, String userId, Long line, Comment comment);
    Comment replyComment(String userId, Long commentId, Comment comment);
    Deliverable submit(Long ceaId, Deliverable deliverable);
    List<ActivityInstance> getInstances(Long id);
    Deliverable getDeliverable(Long deliverableId);
}
