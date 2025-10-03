package io.idihia.validation.annotations;

import io.idihia.validation.validators.MediumFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MediumFieldValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MediumField {

	String message() default "Size must not exceed 500 characters";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
