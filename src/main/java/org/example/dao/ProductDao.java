package org.example.dao;

import org.example.domain.products.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductDao {
	Optional<Long> create(Product user);

	void delete(Long id);

	Optional<Product> getById(Long id);

	Collection<Product> getAll(Long userId);
}