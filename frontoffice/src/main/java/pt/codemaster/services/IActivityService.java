package pt.codemaster.services;

import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.repositories.DeliverableRepository;

import java.util.List;

public interface IActivityService {
    ActivityInstance getInstance(Long id);
    Code getCode(Long id);
    Code saveCode(Code code);
    Code addComment(Long codeId, String userId, Long line, Comment comment);
    Comment replyComment(String userId, Long commentId, Comment comment);
    Deliverable submit(Deliverable deliverable);
    List<ActivityInstance> getInstances(Long id);
}
