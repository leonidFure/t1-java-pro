package org.example.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.BaseResponseDto;
import org.example.dto.users.UserCreateRequestDto;
import org.example.dto.users.UserCreateResponseDto;
import org.example.dto.users.UserResponseDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto dto) {
		return ok(userService.create(dto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<BaseResponseDto> deleteUser(@PathVariable("id") Long userId) {
		return ok(userService.delete(userId));
	}

	@GetMapping("{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
		return ok(userService.getById(id));
	}

	@GetMapping
	public ResponseEntity<Collection<UserResponseDto>> getUsers() {
		return ok(userService.getAll());
	}
}