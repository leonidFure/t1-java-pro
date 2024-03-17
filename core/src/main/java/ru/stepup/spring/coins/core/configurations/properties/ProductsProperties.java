package ru.stepup.spring.coins.core.configurations.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.products")
public class ProductsProperties {
	@Getter
	private final RestTemplateProperties client;

	@ConstructorBinding
	public ProductsProperties(RestTemplateProperties client) {
		this.client = client;
	}

}
