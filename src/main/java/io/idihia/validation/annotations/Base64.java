package io.idihia.validation.annotations;


import io.idihia.validation.validators.Base64Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Base64Validator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Base64 {

	String message() default "Invalid Base64 format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
