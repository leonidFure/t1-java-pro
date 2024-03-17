package ru.stepup.spring.coins.core.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.exceptions.ValidationException;
import ru.stepup.spring.coins.core.services.ProductsService;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;
import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExecuteCoinsRequestProductValidator implements Validator<ExecuteCoinsRequest> {
    private final ProductsService productsService;

    @Override
    public void validate(ExecuteCoinsRequest executeCoinsRequest) {
        log.info("ExecuteCoinsRequestProductValidator: check");
        if (executeCoinsRequest.productId().length() != 10) {
            final var product = productsService.getProductById(executeCoinsRequest.productId())
                    .map(ProductResponseDto::getData)
                    .orElseThrow(() -> new ValidationException("PRODUCT_NOT_FOUND", "Продукт не найден"));
            if (product.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                throw new ValidationException("INVALID_BALANCE", "Маленький баланс =(");
            }
        }
    }
}
