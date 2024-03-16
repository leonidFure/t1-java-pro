package org.example.infrostructure.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.products.Product;
import org.example.infrostructure.dao.ProductDao;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoServiceImpl implements ProductDaoService {
	private final ProductDao productDao;

	@Override
	public Optional<Long> create(Product user) {
		return productDao.create(user);
	}

	@Override
	public void delete(Long id) {
		productDao.delete(id);
	}

	@Override
	public Optional<Product> getById(Long id) {
		return productDao.getById(id);
	}

	@Override
	public Collection<Product> getAll(Long userId) {
		return productDao.getAll(userId);
	}
}
