package pl.kurs.sprawdzianfinalnyzarzyk.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<Pesel, String> {
    @Override
    public void initialize(Pesel constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("\\d{11}");
    }
}
