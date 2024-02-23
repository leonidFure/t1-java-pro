package org.example.domain.users;

import lombok.Data;

@Data(staticConstructor = "of")
public class User {
	private final Long id;
	private final String username;
}
