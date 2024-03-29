package ru.lgore.limits.service;

import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.dto.CoinOperationRequestDto;

public interface ConcurrencyCoinOperationsService {
	Limit addOperation(String userId, CoinOperationRequestDto requestDto);
}
