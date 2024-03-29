package ru.lgore.limits.service;

import ru.lgore.limits.dto.LimitResponseDto;

public interface LimitsService {
	LimitResponseDto findByUserId(String userId);
}
