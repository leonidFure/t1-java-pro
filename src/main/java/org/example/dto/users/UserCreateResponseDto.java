package org.example.dto.users;

import lombok.Data;
import org.example.dto.Status;

@Data(staticConstructor = "of")
public class UserCreateResponseDto {
	private final Status status;
	private final Long id;

	public static UserCreateResponseDto ofStatus(Status status) {
		return of(status, null);
	}
}