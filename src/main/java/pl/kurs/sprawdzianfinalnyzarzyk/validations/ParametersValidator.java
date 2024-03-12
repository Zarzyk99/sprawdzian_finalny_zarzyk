package pl.kurs.sprawdzianfinalnyzarzyk.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;

public class ParametersValidator implements ConstraintValidator<ValidParameters, Map<String, Object>> {
    @Override
    public void initialize(ValidParameters constraintAnnotation) {

    }

    @Override
    public boolean isValid(Map<String, Object> parameters, ConstraintValidatorContext context) {
        if (parameters == null || parameters.isEmpty()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Pole " + entry.getKey() + " nie może być puste")
                        .addConstraintViolation();
                return false;
            }
            if (value instanceof Number && ((Number) value).doubleValue() < 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Pole " + entry.getKey() + " nie może być liczbą ujemną")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
