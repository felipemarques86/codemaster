package pt.codemaster.validators.impl;

import pt.codemaster.adt.Deliverable;
import pt.codemaster.validators.adt.BaseValidator;
import pt.codemaster.validators.adt.EntityValidator;
import pt.codemaster.validators.adt.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class DeliverableValidator extends BaseValidator implements EntityValidator<Deliverable> {

    private static DeliverableValidator INSTANCE;

    private DeliverableValidator() {

    }

    public static EntityValidator<Deliverable> getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DeliverableValidator();
        }
        return INSTANCE;
    }


    @Override
    public List<ValidationError> businessValidation(Deliverable entity) {
        List<ValidationError> errors = new ArrayList<>();
        validateNotNull(entity.getAuthor(), "author", "O Autor do código-fonte é obrigatório", errors);
        errors.addAll(CodeValidator.getInstance().validate(entity.getCode()));
        return errors;
    }
}
