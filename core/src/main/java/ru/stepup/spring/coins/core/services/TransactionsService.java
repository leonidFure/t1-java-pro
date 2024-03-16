package ru.stepup.spring.coins.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.entities.Transaction;
import ru.stepup.spring.coins.core.repositories.TransactionsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    public Transaction save(Transaction transaction) {
        return transactionsRepository.save(transaction);
    }

    public Optional<Transaction> findByUuidAndUserId(String uuid, String userId) {
        return transactionsRepository.findByUuidAndUserId(uuid, userId);
    }
}