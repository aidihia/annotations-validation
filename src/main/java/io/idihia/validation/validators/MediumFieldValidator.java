package io.idihia.validation.validators;


import io.idihia.validation.annotations.MediumField;

public class MediumFieldValidator extends AbstractSizeValidator<MediumField> {

	@Override
	public void initialize(MediumField annotation) {
		this.maxSize = 500;
	}

}