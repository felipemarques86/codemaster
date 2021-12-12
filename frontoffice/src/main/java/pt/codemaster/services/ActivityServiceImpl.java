package pt.codemaster.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.repositories.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private ActivityDefinitionRepository activityDefinitionRepository;

    @Autowired
    private ActivityInstanceRepository activityInstanceRepository;

    @Autowired
    private EndUserRepository endUserRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DeliverableRepository deliverableRepository;

    @Override
    public ActivityInstance getInstance(Long id) {
        return activityInstanceRepository.findById(id).orElse(null);
    }


    @Override
    public Activity saveActivity(Activity activity){
        logger.info(activity.toString());
        return activityDefinitionRepository.save(activity);
    }

    @Override
    public Activity getActivity(Long id) {
        return activityDefinitionRepository.findById(id).orElse(null);
    }

    @Override
    public ActivityInstance createInstance(Long id, String userId, Activity act) {
        Optional<Activity> activityOptional = activityDefinitionRepository.findById(id);
        if(activityOptional.isPresent()) {
            Activity activity = activityOptional.get();
            act.setId(activity.getId());
            activityDefinitionRepository.save(act);
            return getAvailableInstance(act, userId);
        } else {
            act.setId(id);
            act = activityDefinitionRepository.save(act);
            return getAvailableInstance(act, userId);
        }
    }

    @Override
    public Code saveCode(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public EndUser saveEndUser(EndUser endUser) {
        return endUserRepository.save(endUser);
    }

    @Override
    public Code addComment(Long codeId, String userId, Long line, Comment comment) {

        Optional<Code> codeOptional = codeRepository.findById(codeId);

        if(codeOptional.isPresent()) {
            Code code = codeOptional.get();
            comment.setAuthor(getOrCreateUser(userId));
            comment.setDate(new Date());
            code.add(comment);
            comment = commentRepository.save(comment);
            code.addCommentText(line.intValue(), comment);
            return codeRepository.save(code);
        }
        return null;
    }

    @Override
    public Comment replyComment(String userId, Long commentId, Comment comment) {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isPresent()) {
            Comment parent = commentOptional.get();
            comment.setAuthor(getOrCreateUser(userId));
            comment.setDate(new Date());
            parent.getReplies().add(comment);
            commentRepository.save(parent);
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public Deliverable submit(Deliverable deliverable) {

        if( deliverable != null) {
            deliverable.setSubmitted(true);
            deliverable.setSubmissionDate(new Date());

            return deliverableRepository.save(deliverable);
        }

        return null;
    }

    @Override
    public List<ActivityInstance> getInstances(Long id) {
        return activityInstanceRepository.findAll()
                .stream()
                .filter( activityInstance -> activityInstance.getActivity().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Code getCode(Long id) {
        return codeRepository.findById(id).orElse(null);
    }

    private ActivityInstance getAvailableInstance(Activity activity, String userId) {
        Optional<ActivityInstance> any = activityInstanceRepository.findAll()
                .stream()
                .filter(i -> i.getActivity().getId() == activity.getId() && i.getDeliverable()
                        .stream()
                        .anyMatch(d -> d.getAuthor() == null || d.getAuthor().getId().equals(userId)))
                .findAny();

        ActivityInstance instance = null;

        if(!any.isEmpty()){
            instance = any.get();
            logger.info("Instance found with ID = " +  instance.getId());
        } else {
            instance = new ActivityInstance();
            instance.setActivity(activity);
            instance.setStartDate(new Date());
            List<Deliverable> deliverables = new ArrayList<>();
            for(Solution solution : activity.getSolution()){
                Deliverable deliverable = new Deliverable();
                Code code = solution.getCode();
                Code emptyCode = new Code();
                emptyCode.setLanguage(code.getLanguage());
                deliverable.setCode(emptyCode);
                deliverable.setSolution(solution);
                deliverables.add(deliverable);
            }
            instance.setDeliverable(deliverables);
            logger.info("New instance created");
        }


        Optional<Deliverable> deliverable = instance.getDeliverable().stream().filter(d -> d.getAuthor() == null || d.getAuthor().getId() == userId).findAny();

        if(deliverable.isEmpty()){
            return instance;
        } else {
            Deliverable deliverable1 = deliverable.get();
            deliverable1.setAuthor(getOrCreateUser(userId));
            deliverable1.getCode().setAuthor(getOrCreateUser(userId));
        }

        this.activityInstanceRepository.save(instance);

        return instance;
    }

    private EndUser getOrCreateUser(String userId) {
        Optional<EndUser> optionalEndUser = endUserRepository.findById(userId);
        if(optionalEndUser.isEmpty()) {
            EndUser endUser = new EndUser();
            endUser.setId(userId);
            return endUserRepository.save(endUser);
        } else {
            return optionalEndUser.get();
        }
    }

}
