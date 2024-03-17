package ru.stepup.spring.coins.core.configurations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.configurations.properties.ProductsProperties;
import ru.stepup.spring.coins.core.exceptions.RestTemplateExecutorResponseErrorHandler;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegration;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegrationRestTemplate;
import ru.stepup.spring.coins.core.configurations.properties.ExecutorProperties;

@Configuration
public class IntegrationsConfig {
	@Bean
	@ConditionalOnMissingBean(name = "executorIntegrationRestClient")
	public ExecutorIntegration executorIntegration(ExecutorProperties executorProperties,
												   ResponseErrorHandler restTemplateExecutorResponseErrorHandler) {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri(executorProperties.getClient().getUrl())
				.setConnectTimeout(executorProperties.getClient().getConnectTimeout())
				.setReadTimeout(executorProperties.getClient().getReadTimeout())
				.errorHandler(restTemplateExecutorResponseErrorHandler)
				.build();
		return new ExecutorIntegrationRestTemplate(restTemplate);
	}

	@Bean
	public RestTemplate productRestTemplate(ProductsProperties properties,
											ResponseErrorHandler restTemplateProductsResponseErrorHandler) {
		return new RestTemplateBuilder()
				.rootUri(properties.getClient().getUrl())
				.setConnectTimeout(properties.getClient().getConnectTimeout())
				.setReadTimeout(properties.getClient().getReadTimeout())
				.errorHandler(restTemplateProductsResponseErrorHandler)
				.build();
	}
}