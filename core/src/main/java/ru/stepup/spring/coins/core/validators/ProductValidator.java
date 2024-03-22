package ru.stepup.spring.coins.core.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.exceptions.ValidationException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator<ProductResponseDto> {

	@Override
	public void validate(ProductResponseDto productData) {
		if (productData == null) {
			throw new ValidationException("PRODUCT_NOT_FOUND", "Продукт не найден");
		}
		if (!productData.getStatus().getSuccess()) {
			throw new ValidationException(productData.getStatus().getErrorCode(), productData.getStatus().getErrorMessage());
		}
		if (productData.getData().getBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValidationException("INVALID_BALANCE", "Маленький баланс =(");
		}
	}
}
