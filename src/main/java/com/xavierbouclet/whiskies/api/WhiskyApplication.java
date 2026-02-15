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
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.UUID;

@SpringBootApplication
public class WhiskyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiskyApplication.class, args);
    }

    @Bean
    WhiskyService whiskyService() {

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:3000")
                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(
                        RestClientAdapter.create(restClient)
                ).build();

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
