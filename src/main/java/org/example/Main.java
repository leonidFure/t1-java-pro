package org.example;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		final var applicationContext = new AnnotationConfigApplicationContext("org.example");

		final var userService = applicationContext.getBean(UserService.class);
		final var id = userService.create(new User(1L, "lgorev"));
		System.out.println("Результат добавления пользователя: " + id);

		final var user = userService.getById(id);
		System.out.println("Результат получения пользователя: " + user);

		final var users = userService.getAll();
		System.out.println("Результат получения пользователей: " + users);
		userService.delete(id);
	}
}