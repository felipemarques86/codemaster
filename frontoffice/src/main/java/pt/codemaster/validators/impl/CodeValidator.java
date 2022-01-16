package pt.codemaster.validators.impl;

import pt.codemaster.adt.Code;
import pt.codemaster.validators.adt.BaseValidator;
import pt.codemaster.validators.adt.EntityValidator;
import pt.codemaster.validators.adt.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class CodeValidator extends BaseValidator implements EntityValidator<Code> {

    private static CodeValidator INSTANCE;

    private CodeValidator() {
    }

    public static EntityValidator<Code> getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CodeValidator();
        }
        return INSTANCE;
    }

    @Override
    public List<ValidationError> businessValidation(Code entity) {
        List<ValidationError> errors = new ArrayList<>();
        validateNotNull(entity.getCode(), "code", "O código-fonte não pode estar vazio", errors);
        validateNotNull(entity.getLanguage(), "language", "Deve especificar a linguagem do código-fonte", errors);
        return errors;
    }
}
