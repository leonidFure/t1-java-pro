package ru.stepup.spring.coins.core.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;

@Slf4j
@Component
public class ExecuteCoinsRequestNumberValidator implements Validator<ExecuteCoinsRequest> {
    @Override
    public void validate(ExecuteCoinsRequest executeCoinsRequest) {
        log.info("ExecuteCoinsRequestNumberValidator: check");
        if (executeCoinsRequest.receiverNumber().length() != 10) {
            throw new RuntimeException(); // TODO !!!
        }
    }
}
