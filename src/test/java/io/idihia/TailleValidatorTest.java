package io.idihia;

import io.idihia.validation.annotations.LongField;
import io.idihia.validation.annotations.MediumField;
import io.idihia.validation.annotations.ShortField;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TailleValidatorTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void shouldValidateShortFieldWithCorrectValue() {
		FieldDTO dto = new FieldDTO("A SHORT TITLE", null, null);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isEmpty();
	}

	@Test
	void shouldInvalidateShortFieldExceeding100Characters() {
		String longString = "a".repeat(101);
		FieldDTO dto = new FieldDTO(longString, null, null);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isNotEmpty();
		assertThat(violations.iterator().next().getMessage())
				.isEqualTo("[TECH_03004] The field `title` has a number of characters exceeding the allowed limit: 101 > 100");
	}

	@Test
	void shouldValidateMediumFieldWith500Characters() {
		String mediumString = "a".repeat(500);
		FieldDTO dto = new FieldDTO(null, mediumString, null);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isEmpty();
	}

	@Test
	void shouldInvalidateMediumFieldExceeding500Characters() {
		String tooLong = "a".repeat(501);
		FieldDTO dto = new FieldDTO(null, tooLong, null);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isNotEmpty();
		assertThat(violations.iterator().next().getMessage())
				.isEqualTo("[TECH_03004] The field `description` has a number of characters exceeding the allowed limit: 501 > 500");
	}

	@Test
	void shouldValidateLongFieldUpTo5000Characters() {
		String longContent = "a".repeat(5000);
		FieldDTO dto = new FieldDTO(null, null, longContent);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isEmpty();
	}

	@Test
	void shouldInvalidateLongFieldExceeding5000Characters() {
		String tooLong = "a".repeat(5001);
		FieldDTO dto = new FieldDTO(null, null, tooLong);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isNotEmpty();
		assertThat(violations.iterator().next().getMessage())
				.isEqualTo("[TECH_03004] The field `content` has a number of characters exceeding the allowed limit: 5001 > 5000");
	}

	@Test
	void shouldValidateNullFieldsNotConstrainedByNotNull() {
		FieldDTO dto = new FieldDTO(null, null, null);

		Set<ConstraintViolation<FieldDTO>> violations = validator.validate(dto);

		assertThat(violations).isEmpty();
	}


	// Test DTO as a record
	record FieldDTO(
			@ShortField String title,
			@MediumField String description,
			@LongField String content
	) {}

}
