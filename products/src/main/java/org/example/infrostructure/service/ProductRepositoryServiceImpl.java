package org.example.infrostructure.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.products.Product;
import org.example.infrostructure.entities.ProductEntity;
import org.example.infrostructure.repositories.ProductRepository;
import org.example.service.mappers.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements ProductRepositoryService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public Optional<Long> create(Product product) {
		final var entity = productMapper.toEntity(product);
		return Optional.of(productRepository.save(entity))
				.map(ProductEntity::getId);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> getById(Long id) {
		return productRepository.findById(id)
				.map(productMapper::toDomain);
	}

	@Override
	public Collection<Product> getAll(Long userId) {
		return productRepository.findByUserId(userId).stream()
				.map(productMapper::toDomain)
				.toList();
	}
}
