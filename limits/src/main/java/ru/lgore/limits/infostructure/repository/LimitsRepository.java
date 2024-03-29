package ru.lgore.limits.infostructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.lgore.limits.infostructure.enity.LimitEntity;

import java.util.Optional;

public interface LimitsRepository extends JpaRepository<LimitEntity, String> {
	Optional<LimitEntity> findByUserId(String userId);
}
