package pt.codemaster.services;

import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.repositories.DeliverableRepository;

import java.util.List;

public interface ActivityService {
    ActivityInstance getInstance(Long id);
    Activity saveActivity(Activity activity);
    Activity getActivity(Long id);
    ActivityInstance createInstance(Long id, String userId, Activity activity);
    Code getCode(Long id);
    Code saveCode(Code code);
    EndUser saveEndUser(EndUser endUser);
    Code addComment(Long codeId, String userId, Long line, Comment comment);
    Comment replyComment(String userId, Long commentId, Comment comment);
    Deliverable submit(Deliverable deliverable);
    List<ActivityInstance> getInstances(Long id);
}
