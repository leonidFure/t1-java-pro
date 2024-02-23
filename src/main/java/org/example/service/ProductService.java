package org.example.service;

import org.example.dto.BaseResponseDto;
import org.example.dto.products.CreateProductRequestDto;
import org.example.dto.products.CreateProductResponseDto;
import org.example.dto.products.ProductResponseDto;

import java.util.Collection;

public interface ProductService {
	Collection<ProductResponseDto> getProducts(Long userId);

	ProductResponseDto getProduct(Long productId);

	CreateProductResponseDto createProduct(CreateProductRequestDto dto);

	BaseResponseDto deleteProduct(Long id);
}