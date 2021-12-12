package pt.codemaster.validators.impl;

import pt.codemaster.adt.Solution;
import pt.codemaster.validators.adt.ValidationError;
import pt.codemaster.validators.adt.BaseValidator;
import pt.codemaster.validators.adt.EntityValidator;

import java.util.ArrayList;
import java.util.List;

public class SolutionValidator extends BaseValidator implements EntityValidator<Solution> {

    private static SolutionValidator INSTANCE;
    private SolutionValidator() {

    }

    public static SolutionValidator getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SolutionValidator();
        }
        return INSTANCE;
    }


    @Override
    public List<ValidationError> businessValidation(Solution entity) {
        List<ValidationError> errors = new ArrayList<>();
        validateNotEmpty(entity.getTestsToPass(), "tests", "Deve pelo menos criar um teste por solução", errors);
        validateNotNull(entity.getCode(), "code", "Uma solução de ter um código-fonte associado", errors);
        errors.addAll(CodeValidator.getInstance().validate(entity.getCode()));
        return errors;
    }
}
