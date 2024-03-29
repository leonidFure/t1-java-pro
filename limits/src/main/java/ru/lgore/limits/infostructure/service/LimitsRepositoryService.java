package ru.lgore.limits.infostructure.service;

import org.springframework.data.domain.Page;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.domain.PageCriteria;

import java.util.Collection;
import java.util.Optional;

public interface LimitsRepositoryService {
	String add(Limit limit);

	void update(Limit model);

	void updateAll(Collection<Limit> models);

	Optional<Limit> findByUserId(String userId);

	Page<Limit> findAll(PageCriteria pageable);
}
