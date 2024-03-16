package org.example.dto.products;

import lombok.Builder;
import lombok.Data;
import org.example.domain.products.ProductType;
import org.example.dto.Status;

import java.math.BigDecimal;

@Data(staticConstructor = "of")
public class ProductResponseDto {
	private final Status status;
	private final ProductData data;

	public static ProductResponseDto ofStatus(Status status) {
		return of(status, null);
	}
	@Data
	@Builder
	public static class ProductData {
		private final Long id;
		private final Long userId;
		private final Long productNumber;
		private final BigDecimal balance;
		private final ProductType type;
	}
}