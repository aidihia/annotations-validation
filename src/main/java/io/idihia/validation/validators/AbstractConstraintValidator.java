package io.idihia.validation.validators;

import io.idihia.validation.ErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.helpers.MessageFormatter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Slf4j
public abstract class AbstractConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

	/**
	 * Builds and applies a violation message that will be returned by the annotation.
	 * <p>
	 * Also logs a warning message as a bonus.
	 * </p>
	 *
	 * @param context the context on which the violation message will be applied
	 * @param error   the error code used to build the message
	 * @param args    the message arguments to format
	 */
	protected void buildViolationAndLog(ConstraintValidatorContext context, ErrorCode error, Object... args) {
		String message = MessageFormatter.format("[{}] {}",
												 error.getCode(),
												 MessageFormatter.arrayFormat(error.getMessage(), args)
																 .getMessage())
										 .getMessage();

		log.warn(message);

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}

	/**
	 * Uses reflection to retrieve the name of the field on which the annotation is placed.
	 * <p>
	 * The field name is stored in a {@code protected} variable of the child context
	 * {@link HibernateConstraintValidatorContext}.
	 * </p>
	 *
	 * @param context the annotation context
	 * @return the field name
	 * @throws RuntimeException if an error occurs while retrieving the field name
	 */
	protected String getFieldName(ConstraintValidatorContext context) {
		try {
			HibernateConstraintValidatorContext unwrappedContext = context.unwrap(HibernateConstraintValidatorContext.class);

			Field field = unwrappedContext.getClass().getDeclaredField("basePath");

			field.setAccessible(true);

			PathImpl path = (PathImpl) field.get(unwrappedContext);

			return path.getLeafNode().getName();

		} catch (NoSuchFieldException | IllegalAccessException exception) {
			log.error("An error occurred while retrieving the field name when writing the violation message");
			throw new RuntimeException(exception);
		}
	}

}

