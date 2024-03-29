package ru.lgore.limits.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lgore.limits.dto.LimitResponseDto;
import ru.lgore.limits.service.LimitsService;
import ru.lgorev.consts.HttpConstants;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitsController {
	private final LimitsService limitsService;

	@GetMapping
	public ResponseEntity<LimitResponseDto> getById(@RequestHeader(HttpConstants.USERID) String userId) {
		return ok(limitsService.findByUserId(userId));
	}
}
