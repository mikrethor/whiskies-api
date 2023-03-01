package com.xavierbouclet.whiskies.api.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "client.whisky.service")
public record WhiskyClientProperties(@NotNull String url) {
}




