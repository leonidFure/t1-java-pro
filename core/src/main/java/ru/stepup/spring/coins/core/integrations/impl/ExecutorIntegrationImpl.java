package ru.stepup.spring.coins.core.integrations.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.CoinsExecuteDtoRq;
import ru.stepup.spring.coins.core.integrations.dtos.CoinsExecuteDtoRs;
import ru.stepup.spring.coins.core.integrations.executor.HttpRequestExecutor;
import ru.stepup.spring.coins.core.utils.HttpRequest;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExecutorIntegrationImpl implements ExecutorIntegration {
	private final HttpRequestExecutor executorRequestExecutor;

	@Override
	public CoinsExecuteDtoRs execute(ExecuteCoinsRequest executeCoinsRequest) {
		final var coinsExecuteDtoRq = new CoinsExecuteDtoRq(
				executeCoinsRequest.receiverNumber(),
				executeCoinsRequest.productId(),
				executeCoinsRequest.productType()
		);
		return executorRequestExecutor.<CoinsExecuteDtoRs>execute(HttpRequest.builder()
						.url("/payments/execute")
						.method(HttpMethod.POST)
						.body(coinsExecuteDtoRq)
						.build())
				.orElseThrow(() -> new RuntimeException("Ошибка интеграции"));
	}
}
