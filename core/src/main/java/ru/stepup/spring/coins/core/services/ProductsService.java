package ru.stepup.spring.coins.core.services;

import ru.stepup.spring.coins.core.dtos.GetProductsResponseDto;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
	Optional<ProductResponseDto> getProductById(String productId);
	List<ProductResponseDto> getProductsByUserId(String userId);
}
