package ru.stepup.spring.coins.core.dtos;

import lombok.Data;
import ru.stepup.spring.coins.core.integrations.dtos.ProductResponseDto;

import java.util.Collection;

@Data(staticConstructor = "of")
public class GetProductsResponseDto {
	private final Collection<ProductResponseDto> products;
}
