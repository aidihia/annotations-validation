package io.idihia.validation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;


@RequiredArgsConstructor
@Getter
public enum CharacterSet {

	/**
	 * Default character set allowing alphanumeric characters, common accents, and basic punctuation.
	 */
	DEFAULT(Pattern.compile("^[0-9a-zA-ZÂÄÀÊËÉÈÎÏÔÖÛÜÙÇŸâäàêëéèîïôöûüùçÿÆæŒœ\\-'. ,()\\t]+$")),

	/***
	 * MORE EXAMPLES
	 */

	/**
	 * Character set for first and last names, excluding parentheses and commas.
	 */
	FIRST_LAST_NAME(Pattern.compile("^[0-9a-zA-ZÂÄÀÊËÉÈÎÏÔÖÛÜÙÇŸâäàêëéèîïôöûüùçÿÆæŒœ\\-'. ]+$")),

	/**
	 * Character set for country and city names, allowing additional punctuation.
	 */
	COUNTRY_CITY(Pattern.compile("^[0-9a-zA-ZÂÄÀÊËÉÈÎÏÔÖÛÜÙÇŸâäàêëéèîïôöûüùçÿÆæŒœ\\-'. ,:;/()°]+$")),

	/**
	 * Character set for free text input, allowing a wide range of special characters.
	 */
	FREE_TEXT(Pattern.compile("^[0-9a-zA-ZÂÄÀÊËÉÈÎÏÔÖÛÜÙÇŸâäàêëéèîïôöûüùçÿÆæŒœ\\-'. ,:;/()°\"«»@_*!?+\\t\\u00A0‘]+$")),

	/**
	 * Character set for phone numbers, allowing digits, plus sign, and common separators.
	 */
	PHONE_NUMBER(Pattern.compile("^[0-9+.\\- ()xXe]+$")),

	/**
	 * Character set for birth dates, allowing digits and common date separators.
	 */
	BIRTH_DATE(Pattern.compile("^[0-9-/.]+$"));

	private final Pattern pattern;
}