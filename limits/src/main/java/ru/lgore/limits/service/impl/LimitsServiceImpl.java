package ru.lgore.limits.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.dto.LimitResponseDto;
import ru.lgore.limits.infostructure.service.LimitsRepositoryService;
import ru.lgore.limits.service.LimitsService;
import ru.lgore.limits.service.mapper.LimitsMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitsServiceImpl implements LimitsService {
	private final LimitsRepositoryService limitsRepositoryService;
	private final LimitsMapper limitsMapper;

	@Value("${app.limit.default-value}")
	private BigDecimal defaultLimit;

	@Override
	public LimitResponseDto findByUserId(String userId) {
		return limitsRepositoryService.findByUserId(userId)
				.or(() -> {
					final var id = limitsRepositoryService.add(Limit.builder()
							.userId(userId)
							.amount(defaultLimit)
							.updateDatetime(LocalDateTime.now())
							.build());
					return limitsRepositoryService.findByUserId(id);
				})
				.map(limitsMapper::toDto)
				.orElseThrow(() -> new IllegalStateException("абсолютно невалидная ситуация"));
	}
}
