package ru.stepup.spring.coins.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.entities.Transaction;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;
import ru.stepup.spring.coins.core.validators.ExecuteCoinsRequestProductValidator;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final TransactionsService transactionsService;
    private final ExecuteCoinsRequestProductValidator executeCoinsRequestProductValidator;

    public ExecuteCoinsResponse execute(ExecuteCoinsRequest request, String userId) {
        if (coreProperties.getNumbersBlockingEnabled()) {
            if (coreProperties.getBlockedNumbers().contains(request.receiverNumber())) {
                throw new BadRequestException("Указан заблокированный номер кошелька", "BLOCKED_ACCOUNT_NUMBER");
            }
        }
        executeCoinsRequestProductValidator.validate(request);
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                userId,
                request.receiverNumber(),
                request.productId(),
                request.productType(),
                null
        );
        transactionsService.save(transaction);
        ExecuteCoinsResponse response = executorService.execute(request);
        // transaction = transactionService.findBy..
        transaction.setTransactionId(response.coinsTransactionId());
        transactionsService.save(transaction);
        return response;
    }
}
