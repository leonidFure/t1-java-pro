package org.example.infrostructure.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.products.ProductType;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "product_number")
	private Long productNumber;
	@Column(name = "balance")
	private BigDecimal balance;
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ProductType type;
}
