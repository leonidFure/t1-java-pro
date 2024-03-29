package ru.lgore.limits.domain;

import lombok.Builder;
import lombok.Data;
import ru.lgore.limits.dto.CoinOperationRequestDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CoinOperation {
	private final String transactionId;
	private final String userId;
	private final BigDecimal amount;
	private final LocalDateTime datetime;
	private final String basedOnKey;

	public static CoinOperation from(String userId, CoinOperationRequestDto requestDto, String basedOnId) {
		return CoinOperation.builder()
				.userId(userId)
				.transactionId(requestDto.getTransactionId())
				.amount(requestDto.getAmount())
				.datetime(LocalDateTime.now())
				.basedOnKey(basedOnId)
				.build();
	}
}
