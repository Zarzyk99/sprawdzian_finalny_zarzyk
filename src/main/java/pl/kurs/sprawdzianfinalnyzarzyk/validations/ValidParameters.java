package pl.kurs.sprawdzianfinalnyzarzyk.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ParametersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParameters {
    String message() default "Nieprawidłowe parametry";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
