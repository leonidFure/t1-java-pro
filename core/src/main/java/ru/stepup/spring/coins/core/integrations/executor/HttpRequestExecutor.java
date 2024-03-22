package ru.stepup.spring.coins.core.integrations.executor;

import ru.stepup.spring.coins.core.utils.HttpRequest;

import java.util.Optional;

public interface HttpRequestExecutor {
	<R> Optional<R> execute(HttpRequest request);
}
