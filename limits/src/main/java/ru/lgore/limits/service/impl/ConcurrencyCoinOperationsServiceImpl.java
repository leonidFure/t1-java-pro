package ru.lgore.limits.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.lgore.limits.domain.CoinOperation;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.dto.CoinOperationRequestDto;
import ru.lgore.limits.infostructure.service.CoinOperationsRepositoryService;
import ru.lgore.limits.infostructure.service.LimitsRepositoryService;
import ru.lgore.limits.service.ConcurrencyCoinOperationsService;
import ru.lgorev.exceptions.ResponseException;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcurrencyCoinOperationsServiceImpl implements ConcurrencyCoinOperationsService {
	private final CoinOperationsRepositoryService coinOperationsRepositoryService;
	private final LimitsRepositoryService limitsRepositoryService;

	/**
	 * При изменении баланса запись с изменением записывается в лог баланса
	 * при двух параллельных списаниях может возникнуть гонка состояния,
	 * чтобы ее избежать делаем строгую последовательность событий в цепочке изменений баланса
	 * для этого каждая запись имеет уникальную ссылку на предыдущую
	 * в таком случае, если мы попытались списать деньги дважды из одной точки,
	 * будет выброшена ошибка и можно пытаться снова (кол-во попыток ограничено)
	 */
	@Override
	@Retryable(maxAttemptsExpression = "${app.operation.max-attempts}",
			backoff = @Backoff(delayExpression = "${app.operation.delay}"),
			exceptionExpression = "message.contains('uidx_unique_based_on')")
	public Limit addOperation(String userId, CoinOperationRequestDto requestDto) {
		final var limit = limitsRepositoryService.findByUserId(userId)
				.orElseThrow(() -> new ResponseException("BALANCE_NOT_FOUND", "Баланс не найден"));
		// проверка такая нужна только при списании средств со счета
		final var newBalance = limit.getAmount().subtract(requestDto.getAmount());
		if (newBalance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseException("NO_MONEY_BRO", "Денег нету =(");
		}
		final var coinOperation = CoinOperation.from(userId, requestDto, limit.getLastTransactionId());
		final var transactionId = coinOperationsRepositoryService.add(coinOperation);
		return Limit.toUpdate(userId, newBalance, transactionId);
	}
}
