package pl.kurs.sprawdzianfinalnyzarzyk.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = PeselValidator.class)
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pesel {
    String message() default "Wrong PESEL!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}