package org.example.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.BaseResponseDto;
import org.example.dto.products.CreateProductRequestDto;
import org.example.dto.products.CreateProductResponseDto;
import org.example.dto.products.ProductResponseDto;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Collection<ProductResponseDto>> getProducts(@RequestParam Long userId) {
		return ok(productService.getProducts(userId));
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
		return ok(productService.getProduct(id));
	}

	@PostMapping
	public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody CreateProductRequestDto dto) {
		return ok(productService.createProduct(dto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<BaseResponseDto> deleteProduct(@PathVariable Long id) {
		return ok(productService.deleteProduct(id));
	}
}