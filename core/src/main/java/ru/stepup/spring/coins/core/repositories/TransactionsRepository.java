package ru.stepup.spring.coins.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.spring.coins.core.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(String userId);
    Optional<Transaction> findByUuidAndUserId(String uuid, String userId);
}