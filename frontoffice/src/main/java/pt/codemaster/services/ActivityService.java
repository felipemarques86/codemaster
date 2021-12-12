package pt.codemaster.services;

import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.Code;
import pt.codemaster.adt.Comment;
import pt.codemaster.adt.EndUser;
import pt.codemaster.adt.activity.Activity;

public interface ActivityService {
    ActivityInstance<Activity> getInstance(Long id);
    Activity saveActivity(Activity activity);
    Activity getActivity(Long id);
    ActivityInstance createInstance(Long id, String userId, Activity activity);
    Code getCode(Long id);
    Code saveCode(Code code);
    EndUser saveEndUser(EndUser endUser);
    Code addComment(Long codeId, String userId, Long line, Comment comment);
    Comment replyComment(String userId, Long commentId, Comment comment);
}
