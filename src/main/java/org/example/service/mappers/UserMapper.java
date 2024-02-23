package org.example.service.mappers;

import org.example.domain.users.User;
import org.example.dto.users.UserCreateRequestDto;
import org.example.dto.users.UserResponseDto;

public interface UserMapper {
	UserResponseDto toResponseDto(User user);

	User toDomain(UserCreateRequestDto requestDto);
}