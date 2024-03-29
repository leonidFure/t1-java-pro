package ru.lgore.limits.infostructure.enity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coin_operations")
public class CoinOperationEntity {
	@Id
	@Column(name = "transaction_id")
	private String transactionId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "datetime")
	private LocalDateTime datetime;
	@Column(name = "based_on_key")
	private String basedOnKey = "__start";
}
