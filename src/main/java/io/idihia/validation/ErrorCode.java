package io.idihia.validation;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

	String getCode();

	String getMessage();

}
