package ru.lgore.limits.service.mapper;

import org.mapstruct.Mapper;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.dto.LimitResponseDto;
import ru.lgore.limits.infostructure.enity.LimitEntity;

@Mapper(componentModel = "spring")
public interface LimitsMapper {
	Limit toDomain(LimitEntity entity);

	LimitEntity toEntity(Limit model);

	LimitResponseDto toDto(Limit limitEntity);
}
