package org.example.dto.products;

import lombok.Data;
import org.example.dto.Status;

@Data(staticConstructor = "of")
public class CreateProductResponseDto {
	private final Status status;
	private final Long id;

	public static CreateProductResponseDto ofStatus(Status status) {
		return of(status, null);
	}
}
