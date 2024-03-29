package ru.lgore.limits.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ru.lgore.limits.infostructure.enity.CoinOperationEntity}
 */
@Data
@Builder
public class CoinOperationRequestDto implements Serializable {
	private final String transactionId;
	private final BigDecimal amount;
}
