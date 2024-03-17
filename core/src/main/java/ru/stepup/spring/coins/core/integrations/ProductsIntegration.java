package ru.stepup.spring.coins.core.integrations;


import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductsIntegration {
	Optional<ProductResponseDto> getProductById(Long productId);

	List<ProductResponseDto> getProductsByUserId(Long userId);
}
