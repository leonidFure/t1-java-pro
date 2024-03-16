package ru.stepup.spring.coins.core.configurations.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "core")
@RequiredArgsConstructor
@Getter
public class CoreProperties {
    private final Boolean numbersBlockingEnabled;
    private final Set<String> blockedNumbers;
    private final Map<String, String> someMapData;
}
