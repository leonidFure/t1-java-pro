package ru.lgore.limits.infostructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lgore.limits.domain.CoinOperation;
import ru.lgore.limits.infostructure.repository.CoinOperationsRepository;
import ru.lgore.limits.infostructure.service.CoinOperationsRepositoryService;
import ru.lgore.limits.service.mapper.CoinOperationsMapper;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinOperationsRepositoryServiceImpl implements CoinOperationsRepositoryService {
	private final CoinOperationsRepository coinOperationsRepository;
	private final CoinOperationsMapper coinOperationsMapper;

	@Override
	public String add(CoinOperation coinOperation) {
		return coinOperationsRepository
				.save(coinOperationsMapper.toEntity(coinOperation))
				.getTransactionId();
	}

	@Override
	public Optional<CoinOperation> findByUserIdTransactionId(String userId, String transactionId) {
		return coinOperationsRepository.findByUserIdAndTransactionId(userId, transactionId)
				.map(coinOperationsMapper::toDomain);
	}

	@Override
	public void delete(String userId, String transactionId) {
		coinOperationsRepository.deleteByUserIdAndTransactionId(userId, transactionId);
	}
}
