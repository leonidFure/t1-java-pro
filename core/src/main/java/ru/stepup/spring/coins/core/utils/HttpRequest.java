package ru.stepup.spring.coins.core.utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Objects;

@Data
@Builder
public class HttpRequest {
	private final String url;
	private final HttpMethod method;
	private final Object body;
}
