package ru.stepup.spring.coins.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.integrations.ProductsIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
	private final ProductsIntegration productsIntegration;

	@Override
	public Optional<ProductResponseDto> getProductById(String productId) {
		return productsIntegration.getProductById(Long.valueOf(productId));
	}

	@Override
	public List<ProductResponseDto> getProductsByUserId(String userId) {
		return productsIntegration.getProductsByUserId(Long.valueOf(userId));
	}
}
