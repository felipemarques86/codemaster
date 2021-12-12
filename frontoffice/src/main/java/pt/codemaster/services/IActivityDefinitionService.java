package pt.codemaster.services;

import pt.codemaster.adt.activity.Activity;

public interface IActivityDefinitionService {
    Activity saveActivity(Activity activity);
    Activity getActivity(Long id);
}
