package ru.lgore.limits.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Limit {
	private final String userId;
	private final BigDecimal amount;
	private final LocalDateTime updateDatetime;
	private final String lastTransactionId;

	public static Limit toUpdate(String userId, BigDecimal limit) {
		return toUpdate(userId, limit, null);
	}

	public static Limit toUpdate(String userId, BigDecimal limit, String lastTransactionId) {
		return Limit.builder()
				.userId(userId)
				.amount(limit)
				.updateDatetime(LocalDateTime.now())
				.lastTransactionId(lastTransactionId)
				.build();
	}
}
