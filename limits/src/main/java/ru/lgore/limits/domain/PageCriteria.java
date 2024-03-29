package ru.lgore.limits.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class PageCriteria {
	private final Integer pageNum;
	private final Integer pageSize;

	public PageCriteria next() {
		return PageCriteria.of(pageNum + 1, pageSize);
	}
}
