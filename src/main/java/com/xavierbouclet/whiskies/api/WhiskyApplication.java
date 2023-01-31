package com.xavierbouclet.whiskies.api;

import com.xavierbouclet.whiskies.api.model.Whisky;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import com.xavierbouclet.whiskies.api.service.WhiskyService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.UUID;

@SpringBootApplication
public class WhiskyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiskyApplication.class, args);
    }

    @Bean
    WebClient webClient() {
        return  WebClient.builder().baseUrl("http://localhost:3000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @Bean
    HttpServiceProxyFactory proxyFactory(WebClient client) {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
    }

    @Bean
    WhiskyService whiskyService(HttpServiceProxyFactory factory) {
        return factory.createClient(WhiskyService.class);
    }

    @Bean
    CommandLineRunner commandLineRunner(WhiskyService service, WhiskyRepository repository, ObservationRegistry registry) {
        return args -> {
            var posts = Observation.createNotStarted("json-place-holder.load-whiskies", registry)
                    .lowCardinalityKeyValue("some-value", "88")
                    .observe(service::loadAll);

            Observation.createNotStarted("whisky-repository.save-all", registry)
                    .observe(() -> repository.saveAll(posts.stream().map(whisky -> new Whisky(UUID.nameUUIDFromBytes(whisky.getBottle().getBytes()),
                            whisky.getBottle(),
                            whisky.getPrice(),
                            whisky.getRating(),
                            whisky.getRegion())).toList()));
        };
    }

}
