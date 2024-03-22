package ru.stepup.spring.coins.core.integrations.executor.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.executor.HttpRequestExecutor;
import ru.stepup.spring.coins.core.utils.HttpRequest;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class HttpRequestExecutorImpl implements HttpRequestExecutor {
	private final RestTemplate restTemplate;

	@Override
	public <R> Optional<R> execute(HttpRequest request) {
		final var httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		if (request.getHeaders() != null) {
			request.getHeaders()
					.forEach((k,v) -> httpHeaders.addIfAbsent(k, Objects.toString(v)));
		}
		final var httpEntity = new HttpEntity<>(request.getBody(), httpHeaders);
		try {
			log.debug("request: {} {}", request.getMethod(), request.getUrl());
			if (request.getBody() != null) {
				log.debug("body: {}", request.getBody());
			}
			return Optional.of(restTemplate.exchange(request.getUrl(), request.getMethod(), httpEntity, new ParameterizedTypeReference<R>() {
			})).map(HttpEntity::getBody);
		} catch (Exception e) {
			log.error("request error: {}", e.getMessage());
			throw new IntegrationException("", null);
		}
	}
}
