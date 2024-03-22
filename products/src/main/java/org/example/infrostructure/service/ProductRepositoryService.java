package org.example.infrostructure.service;

import org.example.domain.products.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepositoryService {
	Optional<Long> create(Product user);

	void delete(Long id);

	Optional<Product> getById(Long id);

	Collection<Product> getAll(Long userId);
}
