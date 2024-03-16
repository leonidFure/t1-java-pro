package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {
	private final Boolean success;
	private final String errorCode;
	private final String errorMessage;

	public static Status ok() {
		return Status.builder()
				.success(true)
				.build();
	}

	public static Status fail(String errorCode, String errorMessage) {
		return Status.builder()
				.success(false)
				.errorCode(errorCode)
				.errorMessage(errorMessage)
				.build();
	}
}