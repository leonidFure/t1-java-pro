package ru.lgore.limits.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lgore.limits.dto.CoinOperationRequestDto;
import ru.lgore.limits.service.CoinOperationsService;
import ru.lgorev.consts.HttpConstants;
import ru.lgorev.dto.BaseResponseDto;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/coins-operations")
@RequiredArgsConstructor
public class CoinOperationsController {
	private final CoinOperationsService coinOperationsService;

	@PostMapping("add")
	public ResponseEntity<BaseResponseDto> addCoinOperation(@RequestHeader(HttpConstants.USERID) String userId,
															@RequestBody CoinOperationRequestDto requestDto) {
		return ok(coinOperationsService.addCoinOperation(userId, requestDto));
	}

	@PostMapping("{transactionId}/rollback")
	public ResponseEntity<BaseResponseDto> rollbackOperation(@RequestHeader(HttpConstants.USERID) String userId,
															 @PathVariable String transactionId) {
		return ok(coinOperationsService.rollbackOperation(userId, transactionId));
	}

	@Deprecated(since = "для проверки работы в конкурентной среде", forRemoval = true)
	@PostMapping("test")
	public void test(@RequestHeader(HttpConstants.USERID) String userId) {
		IntStream.range(0, 100)
				.mapToObj(it -> CoinOperationRequestDto.builder()
						.transactionId(String.valueOf(it))
						.amount(BigDecimal.TEN)
						.build())
				.parallel()
				.forEach(it -> {
					try {
						coinOperationsService.addCoinOperation(userId, it);
					} catch (Exception e) {
						log.error("err: {}", e.getMessage());
					}

				});
	}

}
