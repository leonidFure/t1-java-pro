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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "limits")
public class LimitEntity {
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "update_datetime")
	private LocalDateTime updateDatetime;
	@Column(name = "last_transaction_id")
	private String lastTransactionId;
}
