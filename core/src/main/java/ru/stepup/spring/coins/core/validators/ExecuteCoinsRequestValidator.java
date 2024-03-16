package ru.stepup.spring.coins.core.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExecuteCoinsRequestValidator {
    private final List<Validator<ExecuteCoinsRequest>> validators;

    public void validate(ExecuteCoinsRequest executeCoinsRequest) {
        for (Validator<ExecuteCoinsRequest> v : validators) {
            // TODO try-catch
            v.validate(executeCoinsRequest);

        }
    }
}
