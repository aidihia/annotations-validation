package io.idihia.validation.annotations;

import io.idihia.validation.enums.CharacterSet;
import io.idihia.validation.validators.AllowedCharactersValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AllowedCharactersValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
public @interface AllowedCharacters {

	String message() default "A validation problem has occurred";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	CharacterSet value() default CharacterSet.DEFAULT;

}
