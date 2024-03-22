package ru.stepup.spring.coins.core.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.exceptions.ValidationException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExecuteCoinsRequestProductValidator implements Validator<ExecuteCoinsRequest> {

    @Override
    public void validate(ExecuteCoinsRequest executeCoinsRequest) {
        log.info("ExecuteCoinsRequestProductValidator: check");
        if (executeCoinsRequest.productId().length() != 10) {
            throw new ValidationException("INVALID_PRODUCT_ID", "Невалидный идентифиактор продукта");
        }
    }
}
