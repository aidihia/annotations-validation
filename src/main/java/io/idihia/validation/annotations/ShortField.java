package io.idihia.validation.annotations;

import io.idihia.validation.validators.ShortFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ShortFieldValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShortField {

	String message() default "Size must not exceed 100 characters";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
