package ru.lgore.limits.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link ru.lgore.limits.infostructure.enity.LimitEntity}
 */
@Data
@Builder
public class LimitResponseDto implements Serializable {
	private final String userId;
	private final BigDecimal amount;
	private final LocalDateTime updateDatetime;
	private final String lastTransactionId;
}