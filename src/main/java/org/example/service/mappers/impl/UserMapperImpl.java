package org.example.service.mappers.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.users.User;
import org.example.dto.Status;
import org.example.dto.users.UserCreateRequestDto;
import org.example.dto.users.UserResponseDto;
import org.example.service.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserMapperImpl implements UserMapper {

	@Override
	public UserResponseDto toResponseDto(User user) {
		return UserResponseDto.of(
				Status.ok(),
				UserResponseDto.UserData.of(user.getId(), user.getUsername()));
	}

	@Override
	public User toDomain(UserCreateRequestDto requestDto) {
		return User.of(null, requestDto.getUsername());
	}
}