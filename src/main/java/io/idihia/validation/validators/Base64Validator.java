package io.idihia.validation.validators;

import io.idihia.validation.annotations.Base64;
import io.idihia.validation.enums.TechnicalErrorCode;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link Base64} annotation.
 * <p>
 * Validates that a string is properly Base64 encoded by attempting to decode it.
 * If decoding fails, a validation error is reported.
 * </p>
 */
public class Base64Validator extends AbstractConstraintValidator<Base64, String> {

	@Override
	public boolean isValid(String stringToValidate, ConstraintValidatorContext context) {

		if (stringToValidate == null) return true;

		try {
			// If decoding succeeds, there is no validation problem
			java.util.Base64.getDecoder().decode(stringToValidate);
			return true;

			// Otherwise, an exception is thrown with the corresponding validation error message
		} catch (IllegalArgumentException exception) {
			buildViolationAndLog(context, TechnicalErrorCode.BASE64_REJECTION, getFieldName(context), exception.getMessage());
		}

		return false;
	}
}
