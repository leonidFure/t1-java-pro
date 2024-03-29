package ru.lgorev.dto;

import lombok.Data;

@Data(staticConstructor = "of")
public class BaseResponseDto {
	private final Status status;
}