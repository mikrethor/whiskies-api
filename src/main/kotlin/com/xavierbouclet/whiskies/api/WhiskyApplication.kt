package com.xavierbouclet.whiskies.api

import com.xavierbouclet.whiskies.api.configuration.WhiskyClientProperties
import com.xavierbouclet.whiskies.api.model.Whisky
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository
import com.xavierbouclet.whiskies.api.service.WhiskyService
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationRegistry
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import java.util.*
import java.util.function.Supplier

@SpringBootApplication
@EnableConfigurationProperties(WhiskyClientProperties::class)
class WhiskyApplication {
    @Bean
    fun webClient(whiskyClientProperties: WhiskyClientProperties) =
        WebClient.builder()
            .baseUrl(whiskyClientProperties.url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()


    @Bean
    fun proxyFactory(client: WebClient) =
        HttpServiceProxyFactory
            .builder(WebClientAdapter.forClient(client))
            .build()


    @Bean
    fun whiskyService(proxyFactory: HttpServiceProxyFactory) = proxyFactory.createClient(WhiskyService::class.java)


    @Bean
    fun commandLineRunner(
        service: WhiskyService,
        repository: WhiskyRepository,
        registry: ObservationRegistry
    ): CommandLineRunner {
        return CommandLineRunner { _: Array<String> ->
            val whiskies = Observation.createNotStarted("json-place-holder.load-whiskies", registry)
                .lowCardinalityKeyValue("some-value", "88")
                .observe<List<Whisky>> { service.loadAll() }
            Observation.createNotStarted("whisky-repository.save-all", registry)
                .observe(Supplier {
                    repository.saveAll(whiskies.stream().map { whisky: Whisky ->
                        Whisky(
                            id = UUID.nameUUIDFromBytes(whisky.bottle.toByteArray()),
                            bottle = whisky.bottle,
                            price = whisky.price,
                            rating = whisky.rating,
                            region = whisky.region
                        )
                    }.toList())
                })
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(WhiskyApplication::class.java, *args)
}