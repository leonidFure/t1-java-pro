package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.ProductDao;
import org.example.dto.BaseResponseDto;
import org.example.dto.Status;
import org.example.dto.products.CreateProductRequestDto;
import org.example.dto.products.CreateProductResponseDto;
import org.example.dto.products.ProductResponseDto;
import org.example.service.ProductService;
import org.example.service.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final ProductMapper productMapper;

	@Override
	public Collection<ProductResponseDto> getProducts(Long userId) {
		return productDao.getAll(userId).stream()
				.map(productMapper::toResponseDto)
				.collect(Collectors.toSet());
	}

	@Override
	public ProductResponseDto getProduct(Long productId) {
		return productDao.getById(productId)
				.map(productMapper::toResponseDto)
				.orElseGet(() -> ProductResponseDto.ofStatus(Status.fail(
						"PRODUCT_NOT_FOUND", "Продукт не найден")));
	}

	@Override
	public CreateProductResponseDto createProduct(CreateProductRequestDto dto) {
		return productDao.create(productMapper.toDomain(dto))
				.map(id -> CreateProductResponseDto.of(Status.ok(), id))
				.orElseGet(() -> CreateProductResponseDto.ofStatus(Status.fail(
						"CREATE_PRODUCT_ERROR", "Ошибка при создании продукта")));
	}

	@Override
	public BaseResponseDto deleteProduct(Long id) {
		productDao.delete(id);
		return BaseResponseDto.of(Status.ok());
	}
}