package io.idihia.validation.enums;

import io.idihia.validation.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalErrorCode implements ErrorCode {

	BASE64_REJECTION("TECH_03001", "The field `{}` contains an encoding error: {}"),

	ENUM_VALUE_REJECTION("TECH_03002", "The field `{}` has a value *{}* that does not match the enumeration values: {}"),

	INVALID_CHARACTERS_REJECTION("TECH_03003", "The field `{}` contains unauthorized characters: *{}*"),

	FIELD_SIZE_REJECTION("TECH_03004", "The field `{}` has a number of characters exceeding the allowed limit: {} > {}");


	private final String code;

	private final String message;

}