package ru.lgore.limits.infostructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.domain.PageCriteria;
import ru.lgore.limits.infostructure.repository.LimitsRepository;
import ru.lgore.limits.infostructure.service.LimitsRepositoryService;
import ru.lgore.limits.service.mapper.LimitsMapper;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitsRepositoryServiceImpl implements LimitsRepositoryService {
	private static final String SORTED_FIELDS = "updateDatetime";

	private final LimitsRepository limitsRepository;
	private final LimitsMapper limitsMapper;

	@Override
	public String add(Limit limit) {
		return limitsRepository
				.save(limitsMapper.toEntity(limit))
				.getUserId();
	}

	@Override
	public void update(Limit model) {
		limitsRepository.save(limitsMapper.toEntity(model));
	}

	@Override
	public void updateAll(Collection<Limit> models) {
		limitsRepository.saveAll(models.stream()
				.map(limitsMapper::toEntity)
				.toList());
	}

	@Override
	public Optional<Limit> findByUserId(String userId) {
		return limitsRepository.findByUserId(userId)
				.map(limitsMapper::toDomain);
	}

	@Override
	public Page<Limit> findAll(PageCriteria page) {
		final var pageable = PageRequest.of(page.getPageNum(), page.getPageSize(), Sort.by(SORTED_FIELDS));
		return limitsRepository.findAll(pageable)
				.map(limitsMapper::toDomain);
	}
}
