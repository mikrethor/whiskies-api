package com.xavierbouclet.whiskies.api.configuration

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "client.whisky.service")
data class WhiskyClientProperties(@field:NotBlank val url: String)