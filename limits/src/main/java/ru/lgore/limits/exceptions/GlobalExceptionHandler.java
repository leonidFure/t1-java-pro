package ru.lgore.limits.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lgorev.dto.BaseResponseDto;
import ru.lgorev.dto.Status;
import ru.lgorev.exceptions.ResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<BaseResponseDto> handle(ResponseException e) {
		final var responseDto = BaseResponseDto.of(Status.fail(e.getErrorCode(), e.getMessage()));
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
