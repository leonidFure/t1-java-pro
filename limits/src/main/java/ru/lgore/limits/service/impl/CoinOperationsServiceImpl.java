package ru.lgore.limits.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.dto.CoinOperationRequestDto;
import ru.lgore.limits.infostructure.service.CoinOperationsRepositoryService;
import ru.lgore.limits.infostructure.service.LimitsRepositoryService;
import ru.lgore.limits.service.CoinOperationsService;
import ru.lgore.limits.service.ConcurrencyCoinOperationsService;
import ru.lgorev.dto.BaseResponseDto;
import ru.lgorev.dto.Status;
import ru.lgorev.exceptions.ResponseException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinOperationsServiceImpl implements CoinOperationsService {
	private final CoinOperationsRepositoryService coinOperationsRepositoryService;
	private final LimitsRepositoryService limitsRepositoryService;
	private final ConcurrencyCoinOperationsService concurrencyCoinOperationsService;

	@Override
	public BaseResponseDto addCoinOperation(String userId, CoinOperationRequestDto requestDto) {
		// Обновление баланса лучше бы делать отдельно и вызывать асинхронно, согласно шаблону CQRS
		// это позволит уменьшить нагрузку на расчет операций,
		// но в данном случае обойдемся реализацией в рамках одной транзакции
		final var limit = concurrencyCoinOperationsService.addOperation(userId, requestDto);
		limitsRepositoryService.update(limit);
		return BaseResponseDto.of(Status.ok());
	}

	@Override
	@Transactional
	public BaseResponseDto rollbackOperation(String userId, String transactionId) {
		final var operation = coinOperationsRepositoryService.findByUserIdTransactionId(userId, transactionId)
				.orElseThrow(() -> new ResponseException("OPERATION_NOT_FOUND", "Операция не найдена"));
		coinOperationsRepositoryService.delete(userId, transactionId);
		final var currentAmount = limitsRepositoryService.findByUserId(userId)
				.map(Limit::getAmount)
				.orElseThrow(() -> new ResponseException("LIMIT_NOT_FOUND", "Лимит не найден"));
		final var newLimit = currentAmount.add(operation.getAmount());
		limitsRepositoryService.update(Limit.toUpdate(userId, newLimit, transactionId));
		return BaseResponseDto.of(Status.ok());
	}

}
