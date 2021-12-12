package pt.codemaster.validators;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public abstract class BaseValidator {
    protected final void validateNotNull(String value, String field, String message, List<ValidationError> errors) {
        if(StringUtils.isEmpty(value)){
            errors.add(new ValidationError(field, message));
        }
    }

    protected final void validateNotNull(Object value, String field, String message, List<ValidationError> errors) {
        if(null == value){
            errors.add(new ValidationError(field, message));
        }
    }


    protected final void validateNotEmpty(List<?> list, String field, String message, List<ValidationError> errors) {
        if(list == null || list.isEmpty()) {
            errors.add(new ValidationError(field, message));
        }
    }
}
