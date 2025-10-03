package io.idihia.validation.validators;


import io.idihia.validation.annotations.LongField;

public class LongFieldValidator extends AbstractSizeValidator<LongField> {

	@Override
	public void initialize(LongField annotation) {
		this.maxSize = 5000;
	}

}