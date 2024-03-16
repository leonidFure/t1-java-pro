package org.example.domain.exceptions;

public class DataBaseException extends RuntimeException {
	public DataBaseException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Ошибка при работе с базой данных: " +  super.getMessage();
	}
}
