package ru.stepup.spring.coins.core.integrations.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.stepup.spring.coins.core.integrations.ProductsIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;
import ru.stepup.spring.coins.core.integrations.executor.HttpRequestExecutor;
import ru.stepup.spring.coins.core.utils.HttpRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.stepup.spring.coins.core.utils.HttpRequestConstants.USERID;

@Component
@RequiredArgsConstructor
public class ProductsIntegrationImpl implements ProductsIntegration {
	private final HttpRequestExecutor productRequestExecutor;

	@Override
	public Optional<ProductResponseDto> getProductById(Long productId) {
		return productRequestExecutor.execute(HttpRequest.builder()
				.url(UriComponentsBuilder.fromHttpUrl("/api/v1/products")
						.queryParam("id", productId)
						.toUriString())
				.method(HttpMethod.GET)
				.build());
	}

	@Override
	public List<ProductResponseDto> getProductsByUserId(Long userId) {
		return productRequestExecutor.<List<ProductResponseDto>>execute(HttpRequest.builder()
						.url("/api/v1/products/")
						.method(HttpMethod.GET)
						.headers(Map.of(USERID, userId))
						.build())
				.orElseThrow();
	}
}
