package ru.lgore.limits.service.schedulers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.lgore.limits.domain.Limit;
import ru.lgore.limits.domain.PageCriteria;
import ru.lgore.limits.infostructure.service.LimitsRepositoryService;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor

public class SchedulerResetLimitsTask {
	private final LimitsRepositoryService limitsRepositoryService;

	@Value("${app.limit.default-value}")
	private BigDecimal defaultLimit;
	@Value("${app.schedulers.limit.batch-size:500}")
	private Integer pageSize;

	@Scheduled(cron = "${app.schedulers.limit.cron}")
	public void run() {
		log.debug("Старт задачи на сброс лимитов");
		var criteria = PageCriteria.of(1, pageSize);
		for (; ; criteria = criteria.next()) {
			try {
				final var limits = limitsRepositoryService.findAll(criteria).stream()
						.map(it -> Limit.toUpdate(it.getUserId(), defaultLimit))
						.toList();
				if (CollectionUtils.isEmpty(limits)) {
					break;
				}
				limitsRepositoryService.updateAll(limits);
				log.debug("Обновлены лимиты. Страница: {}", criteria.getPageNum());
			} catch (Exception e) {
				log.error("Ошибка при сбросе лимитов: {}", e.getMessage());
				return;
			}
		}
		log.debug("Окончание задачи на сброс лимитов");
	}

	@PostConstruct
	void init() {
		log.info("limits reset scheduler registered");
	}
}
