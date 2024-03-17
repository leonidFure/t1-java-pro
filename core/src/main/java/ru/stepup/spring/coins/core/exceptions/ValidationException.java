package ru.stepup.spring.coins.core.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
	private final String errorCode;

	public ValidationException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
