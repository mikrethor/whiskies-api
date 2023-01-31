package com.xavierbouclet.demo;

import com.xavierbouclet.demo.model.Whisky;
import com.xavierbouclet.demo.repository.WhiskyRepository;
import com.xavierbouclet.demo.service.WhiskyService;
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
    CommandLineRunner commandLineRunner(WhiskyRepository repository, ObservationRegistry registry) {
        return args -> {

            WebClient client = WebClient.builder().baseUrl("http://localhost:3000")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
            HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
            WhiskyService service = factory.createClient(WhiskyService.class);

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
