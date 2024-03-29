package ru.lgore.limits.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.lgore.limits.domain.CoinOperation;
import ru.lgore.limits.infostructure.enity.CoinOperationEntity;

@Mapper(componentModel = "spring")
public interface CoinOperationsMapper {
	CoinOperation toDomain(CoinOperationEntity entity);

	@Mapping(target = "basedOnKey", source = "basedOnKey",
			nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	CoinOperationEntity toEntity(CoinOperation coinOperation);
}
