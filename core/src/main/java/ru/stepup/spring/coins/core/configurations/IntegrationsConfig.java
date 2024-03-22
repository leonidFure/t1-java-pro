package ru.stepup.spring.coins.core.configurations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.configurations.properties.ExecutorProperties;
import ru.stepup.spring.coins.core.configurations.properties.ProductsProperties;
import ru.stepup.spring.coins.core.integrations.executor.HttpRequestExecutor;
import ru.stepup.spring.coins.core.integrations.executor.impl.HttpRequestExecutorImpl;

@Configuration
public class IntegrationsConfig {

	@Bean
	public HttpRequestExecutor executorRequestExecutor(ExecutorProperties executorProperties,
													   ResponseErrorHandler restTemplateExecutorResponseErrorHandler) {
		final var restTemplate = new RestTemplateBuilder()
				.rootUri(executorProperties.getClient().getUrl())
				.setConnectTimeout(executorProperties.getClient().getConnectTimeout())
				.setReadTimeout(executorProperties.getClient().getReadTimeout())
				.errorHandler(restTemplateExecutorResponseErrorHandler)
				.build();
		return new HttpRequestExecutorImpl(restTemplate);
	}

	@Bean
	public HttpRequestExecutor productRequestExecutor(ProductsProperties properties,
													  ResponseErrorHandler restTemplateProductsResponseErrorHandler) {
		final var restTemplate = new RestTemplateBuilder()
				.rootUri(properties.getClient().getUrl())
				.setConnectTimeout(properties.getClient().getConnectTimeout())
				.setReadTimeout(properties.getClient().getReadTimeout())
				.errorHandler(restTemplateProductsResponseErrorHandler)
				.build();
		return new HttpRequestExecutorImpl(restTemplate);
	}
}