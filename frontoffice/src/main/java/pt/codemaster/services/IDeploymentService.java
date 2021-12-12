package pt.codemaster.services;

import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.activity.Activity;

public interface IDeploymentService {
    ActivityInstance createInstance(Long id, String userId, Activity activity);
}
