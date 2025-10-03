package io.idihia.validation.validators;

import io.idihia.validation.enums.TechnicalErrorCode;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public abstract class AbstractSizeValidator<A extends Annotation> extends AbstractConstraintValidator<A, String> {

	protected int maxSize;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) return true; // let @NotNull handle nulls if needed
		if (value.length() <= maxSize) return true;

		buildViolationAndLog(context, TechnicalErrorCode.FIELD_SIZE_REJECTION, getFieldName(context), value.length(), maxSize);

		return false;
	}
}

