package org.example.infrostructure.repositories;

import org.example.infrostructure.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	Optional<ProductEntity> findByUserId(Long userId);
}
