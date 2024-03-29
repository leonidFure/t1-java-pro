package ru.lgore.limits.infostructure.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lgore.limits.infostructure.enity.CoinOperationEntity;

import java.util.Optional;

public interface CoinOperationsRepository extends CrudRepository<CoinOperationEntity, String> {
	Optional<CoinOperationEntity> findByUserIdAndTransactionId(String userId, String transactionId);

	void deleteByUserIdAndTransactionId(String userId, String transactionId);
}
