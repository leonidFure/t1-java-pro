package org.example.dto.products;

import lombok.Builder;
import lombok.Data;
import org.example.domain.products.ProductType;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductRequestDto {
	private final Long userId;
	private final Long productNumber;
	private final BigDecimal balance;
	private final ProductType type;
}
