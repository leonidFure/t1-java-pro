package ru.stepup.spring.coins.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.spring.coins.core.dtos.GetProductsResponseDto;
import ru.stepup.spring.coins.core.services.ProductsService;

import static org.springframework.http.ResponseEntity.ok;
import static ru.stepup.spring.coins.core.utils.HttpRequestConstants.USERID;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
	private final ProductsService productsService;

	@GetMapping
	public ResponseEntity<GetProductsResponseDto> getProducts(@RequestHeader(USERID) String userId) {
		return ok(GetProductsResponseDto.of(productsService.getProductsByUserId(userId)));
	}
}
