package com.xavierbouclet.whiskies.api;

import com.xavierbouclet.whiskies.api.model.Whisky;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import com.xavierbouclet.whiskies.api.service.WhiskyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class WhiskyApplication {

    private static final Logger log = LoggerFactory.getLogger(WhiskyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WhiskyApplication.class, args);
    }

    @Bean
    WhiskyService whiskyService() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:3000")
                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(
                        WebClientAdapter.create(webClient)
                ).build();

        return factory.createClient(WhiskyService.class);
    }

    @Bean
    CommandLineRunner seed(WhiskyService service, WhiskyRepository repository) {
        return _ -> repository.saveAll(
                        service.loadAll()
                                .map(w -> new Whisky(
                                        null,
                                        w.getBottle(),
                                        w.getPrice(),
                                        w.getRating(),
                                        w.getRegion()
                                ))
                )
                .doOnNext(w -> log.info("Saved {}", w.getBottle()))
                .doOnError(Throwable::printStackTrace)
                .doOnComplete(() -> log.info("done"))
                .then()
                .block();
    }

}
