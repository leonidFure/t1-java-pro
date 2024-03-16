package org.example.domain.products;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
	private final Long id;
	private final Long userId;
	private final Long productNumber;
	private final BigDecimal balance;
	private final ProductType type;
}