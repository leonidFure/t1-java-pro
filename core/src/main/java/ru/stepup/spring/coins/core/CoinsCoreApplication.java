package ru.stepup.spring.coins.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoinsCoreApplication {
	// Дополнительные способы конфигурирования (мап, списки объектов, создание бинов по условию)
	// service = entity, controller = dto
	// Валидаторы (уровень валидации не контроллер) + логика контроллеров
	// Чуть новой логики (транзакции)
	// Юзер из заголовка
	// По исключению на каждое действие / Дополнительно про обработку ошибок

	public static void main(String[] args) {
		SpringApplication.run(CoinsCoreApplication.class, args);
	}
}
