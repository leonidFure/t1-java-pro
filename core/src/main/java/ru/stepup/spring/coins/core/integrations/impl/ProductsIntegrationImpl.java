package ru.stepup.spring.coins.core.integrations.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.stepup.spring.coins.core.integrations.BaseIntegrationService;
import ru.stepup.spring.coins.core.integrations.ProductsIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.util.List;
import java.util.Optional;

@Component
public class ProductsIntegrationImpl extends BaseIntegrationService implements ProductsIntegration {
	public ProductsIntegrationImpl(RestTemplate productRestTemplate) {
		super(productRestTemplate);
	}

	@Override
	public Optional<ProductResponseDto> getProductById(Long productId) {
		return executeGet("/api/v1/products/" + productId, new ParameterizedTypeReference<>() {
		});
	}

	@Override
	public List<ProductResponseDto> getProductsByUserId(Long userId) {
		final var url = UriComponentsBuilder.fromHttpUrl("/api/v1/products/")
				.queryParam("userId", userId)
				.toUriString();
		return executeGet(url, new ParameterizedTypeReference<List<ProductResponseDto>>() {
		}).orElseThrow();
	}
}
