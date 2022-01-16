package pt.codemaster.validators.impl;

import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.validators.adt.BaseValidator;
import pt.codemaster.validators.adt.EntityValidator;
import pt.codemaster.validators.adt.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ActivityInstanceValidator extends BaseValidator implements EntityValidator<ActivityInstance> {

    private static ActivityInstanceValidator INSTANCE;

    private ActivityInstanceValidator() {

    }

    public static EntityValidator<ActivityInstance> getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActivityInstanceValidator();
        }
        return INSTANCE;
    }

    @Override
    public List<ValidationError> businessValidation(ActivityInstance entity) {
        List<ValidationError> errors = new ArrayList<>();
        validateNotNull(entity.getActivity(), "activity", "Atividade não encontrada", errors);
        validateNotNull(entity.getStartDate(), "startDate", "A data de início é obrigatória", errors);
        validateNotEmpty(entity.getDeliverable(), "deliverable", "Não foram encontradas códigos-fonte para essa atividade", errors);
        errors.addAll(DeliverableValidator.getInstance().businessValidation(entity.getDeliverable()));
        return errors;
    }
}
