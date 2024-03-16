package org.example.dto.users;

import lombok.Data;
import org.example.dto.Status;

@Data(staticConstructor = "of")
public class UserResponseDto {
	private final Status status;
	private final UserData data;

	public static UserResponseDto ofStatus(Status status) {
		return of(status, null);
	}

	@Data(staticConstructor = "of")
	public static class UserData {
		private final Long id;
		private final String username;
	}
}