package ru.lgore.limits.service;

import ru.lgore.limits.dto.CoinOperationRequestDto;
import ru.lgorev.dto.BaseResponseDto;

public interface CoinOperationsService {
	BaseResponseDto addCoinOperation(String userId, CoinOperationRequestDto requestDto);

	BaseResponseDto rollbackOperation(String userId, String transactionId);
}
