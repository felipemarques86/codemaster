package pt.codemaster.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EntityValidator <T>{
    List<ValidationError> businessValidation(T entity);
    default List<ValidationError> businessValidation(List<T> list) {
        if(list == null) {
            return new ArrayList<>();
        }
        return list.stream().flatMap( entity -> businessValidation(entity).stream()).collect(Collectors.toList());
    }
    default List<ValidationError> validate(T entity) {
        List<ValidationError> errors = new ArrayList<>();
        if(entity == null) {
            errors.add(new ValidationError("entity", "Deve preencher todos os campos"));
        }
        errors.addAll(businessValidation(entity));
        return errors;
    }
}
