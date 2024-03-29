package ru.lgorev.exceptions;

import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {
	private final String errorCode;

	public ResponseException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}
}
