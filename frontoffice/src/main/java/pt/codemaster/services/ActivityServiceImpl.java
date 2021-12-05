package pt.codemaster.services;

import pt.codemaster.adt.ActivityInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.repositories.ActivityDefinitionRepository;
import pt.codemaster.repositories.ActivityInstanceRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

    private Logger logger = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private ActivityDefinitionRepository activityDefinitionRepository;

    @Autowired
    private ActivityInstanceRepository activityInstanceRepository;

    @Override
    public ActivityInstance<Activity> getInstance(Long id) {
        return activityInstanceRepository.findById(id).orElse(null);
    }


    @Override
    public Activity saveActivity(Activity activity){
        logger.info(activity.toString());
        return activityDefinitionRepository.save(activity);
    }

}
