package com.xavierbouclet.whiskies.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "client.whisky.service")
@ConstructorBinding
public class WhiskyClientProperties {

    @NotNull
    private final String url;

    public WhiskyClientProperties(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}




