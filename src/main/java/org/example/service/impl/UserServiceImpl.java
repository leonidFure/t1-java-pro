package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.UsersDao;
import org.example.dto.BaseResponseDto;
import org.example.dto.Status;
import org.example.dto.users.UserCreateRequestDto;
import org.example.dto.users.UserCreateResponseDto;
import org.example.dto.users.UserResponseDto;
import org.example.service.UserService;
import org.example.service.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UsersDao usersDao;
	private final UserMapper userMapper;

	@Override
	public UserResponseDto getById(Long id) {
		return usersDao.getById(id)
				.map(userMapper::toResponseDto)
				.orElseGet(() -> UserResponseDto.ofStatus(Status.fail(
						"USER_NOT_FOUND", "Пользователь не найден")));
	}

	@Override
	public Collection<UserResponseDto> getAll() {
		return usersDao.getAll().stream()
				.map(userMapper::toResponseDto)
				.collect(Collectors.toSet());
	}

	@Override
	public UserCreateResponseDto create(UserCreateRequestDto user) {
		return usersDao.create(userMapper.toDomain(user))
				.map(id -> UserCreateResponseDto.of(Status.ok(), id))
				.orElseGet(() -> UserCreateResponseDto.ofStatus(Status.fail(
						"CREATE_USER_ERROR", "Ошибка при создании пользователя")));
	}

	@Override
	public BaseResponseDto delete(Long id) {
		usersDao.delete(id);
		return BaseResponseDto.of(Status.ok());
	}
}