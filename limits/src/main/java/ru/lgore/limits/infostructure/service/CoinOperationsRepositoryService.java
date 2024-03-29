package ru.lgore.limits.infostructure.service;

import ru.lgore.limits.domain.CoinOperation;

import java.util.Optional;

public interface CoinOperationsRepositoryService {

	String add(CoinOperation coinOperation);

	Optional<CoinOperation> findByUserIdTransactionId(String userId, String transactionId);

	void delete(String userId, String transactionId);
}
