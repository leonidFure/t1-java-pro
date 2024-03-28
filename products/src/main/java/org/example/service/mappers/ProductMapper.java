package org.example.service.mappers;

import org.example.domain.products.Product;
import org.example.dto.products.CreateProductRequestDto;
import org.example.dto.products.ProductResponseDto;
import org.example.infrostructure.entities.ProductEntity;

public interface ProductMapper {
	ProductResponseDto toResponseDto(Product product);

	Product toDomain(CreateProductRequestDto dto);

	ProductEntity toEntity(Product product);

	Product toDomain(ProductEntity entity);
}