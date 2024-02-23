package org.example.service;

import org.example.dto.BaseResponseDto;
import org.example.dto.users.UserCreateRequestDto;
import org.example.dto.users.UserCreateResponseDto;
import org.example.dto.users.UserResponseDto;

import java.util.Collection;

public interface UserService {
	UserResponseDto getById(Long id);

	Collection<UserResponseDto> getAll();

	UserCreateResponseDto create(UserCreateRequestDto user);

	BaseResponseDto delete(Long id);
}