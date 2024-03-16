package org.example.dto.users;

import lombok.Data;

@Data(staticConstructor = "of")
public class UserCreateRequestDto {
	private final String username;
}