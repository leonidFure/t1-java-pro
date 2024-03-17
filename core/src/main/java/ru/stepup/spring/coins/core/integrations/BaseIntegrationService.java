package ru.stepup.spring.coins.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseIntegrationService {
	private final RestTemplate restTemplate;

	protected <R> Optional<R> executeGet(String url, ParameterizedTypeReference<R> returnType) {
		return execute(url, HttpMethod.GET, null, returnType);
	}

	protected <T, R> Optional<R> executePost(String url, T body, ParameterizedTypeReference<R> returnType) {
		return execute(url, HttpMethod.POST, body, returnType);
	}

	protected <T, R> Optional<R> executePut(String url, T body, ParameterizedTypeReference<R> returnType) {
		return execute(url, HttpMethod.PUT, body, returnType);
	}

	protected <T, R> Optional<R> executeDelete(String url, T body, ParameterizedTypeReference<R> returnType) {
		return execute(url, HttpMethod.DELETE, body, returnType);
	}

	protected <T, R> Optional<R> execute(String url, HttpMethod httpMethod, T body, ParameterizedTypeReference<R> returnType) {
		final var httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		final var request = new HttpEntity<>(body, httpHeaders);
		try {
			return Optional.of(restTemplate.exchange(url, httpMethod, request, returnType))
					.map(HttpEntity::getBody);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
