package pt.codemaster.validators.impl;

import pt.codemaster.adt.activity.Activity;
import pt.codemaster.validators.adt.BaseValidator;
import pt.codemaster.validators.adt.EntityValidator;
import pt.codemaster.validators.adt.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ActivityValidator extends BaseValidator implements EntityValidator<Activity> {

    private static ActivityValidator INSTANCE;

    private ActivityValidator() {

    }

    public static EntityValidator<Activity> getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActivityValidator();
        }
        return INSTANCE;
    }

    @Override
    public List<ValidationError> businessValidation(Activity entity) {
        List<ValidationError> errors = new ArrayList<>();
        validateNotNull(entity.getName(), "name", "'Nome' é de preenchimento obrigatório", errors);
        validateNotNull(entity.getDescription(), "description", "'Descrição' é de preenchimento obrigatório", errors);
        validateNotEmpty(entity.getSolution(), "solution", "Deve adicionar pelo menos uma solução", errors);
        errors.addAll( SolutionValidator.getInstance().businessValidation(entity.getSolution()) );
        return errors;
    }

}
