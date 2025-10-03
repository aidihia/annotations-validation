package io.idihia.validation.validators;

import io.idihia.validation.annotations.AllowedCharacters;
import io.idihia.validation.enums.CharacterSet;
import io.idihia.validation.enums.TechnicalErrorCode;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Validator for the {@link AllowedCharacters} annotation.
 * <p>
 * Validates that a string contains only characters that match the specified character set pattern.
 * Invalid characters are collected and reported in the violation message.
 * </p>
 */
public class AllowedCharactersValidator extends AbstractConstraintValidator<AllowedCharacters, String> {

	private CharacterSet characterSet;

	@Override
	public void initialize(AllowedCharacters constraintAnnotation) {
		this.characterSet = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String stringToValidate, ConstraintValidatorContext context) {

		if (stringToValidate == null) return true;

		// Retrieve a list of invalid characters if present
		Set<String> invalidCharacters = new HashSet<>();

		stringToValidate.chars()
						.mapToObj(character -> (char) character)
						.map(String::valueOf)
						.filter(character -> !characterSet.getPattern().matcher(character).matches())
						.forEach(invalidCharacters::add);


		// If the list of invalid characters is empty, then the value is valid
		if (invalidCharacters.isEmpty()) return true;

		// Otherwise, build the error message
		buildViolationAndLog(context,
							 TechnicalErrorCode.INVALID_CHARACTERS_REJECTION,
							 getFieldName(context),
							 String.join(", ", invalidCharacters));

		return false;
	}
}
