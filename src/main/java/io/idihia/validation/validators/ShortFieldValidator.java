package io.idihia.validation.validators;


import io.idihia.validation.annotations.ShortField;

public class ShortFieldValidator extends AbstractSizeValidator<ShortField> {

	@Override
	public void initialize(ShortField annotation) {
		this.maxSize = 100;
	}

}