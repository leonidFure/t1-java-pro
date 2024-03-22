package org.example.service.mappers.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.products.Product;
import org.example.dto.Status;
import org.example.dto.products.CreateProductRequestDto;
import org.example.dto.products.ProductResponseDto;
import org.example.infrostructure.entities.ProductEntity;
import org.example.service.mappers.ProductMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductMapperImpl implements ProductMapper {

	@Override
	public ProductResponseDto toResponseDto(Product product) {
		return ProductResponseDto.of(
				Status.ok(),
				ProductResponseDto.ProductData.builder()
						.id(product.getId())
						.userId(product.getUserId())
						.productNumber(product.getProductNumber())
						.balance(product.getBalance())
						.type(product.getType())
						.build());
	}

	@Override
	public Product toDomain(CreateProductRequestDto dto) {
		return Product.builder()
				.userId(dto.getUserId())
				.productNumber(dto.getProductNumber())
				.balance(dto.getBalance())
				.type(dto.getType())
				.build();
	}

	@Override
	public ProductEntity toEntity(Product product) {
		return ProductEntity.builder()
				.userId(product.getUserId())
				.productNumber(product.getProductNumber())
				.balance(product.getBalance())
				.type(product.getType())
				.build();
	}

	@Override
	public Product toDomain(ProductEntity entity) {
		return Product.builder()
				.userId(entity.getUserId())
				.productNumber(entity.getProductNumber())
				.balance(entity.getBalance())
				.type(entity.getType())
				.build();
	}
}