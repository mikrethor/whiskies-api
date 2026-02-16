package com.xavierbouclet.whiskies.api;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.model.Whisky;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import com.xavierbouclet.whiskies.api.service.WhiskyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class WhiskyApplication {

    private static final Logger log = LoggerFactory.getLogger(WhiskyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WhiskyApplication.class, args);
    }

    @Bean
    WhiskyService whiskyService() {
        return HttpServiceProxyFactory.builderFor(
                WebClientAdapter.create(WebClient.builder()
                        .baseUrl("http://localhost:3000")
                        .build())
        ).build().createClient(WhiskyService.class);
    }

    @Bean
    public RouterFunction<ServerResponse> whiskyRoutes(WhiskyRepository repository) {
        return route()
                .path("/api/whiskies", builder -> builder
                        .GET("", _ -> ok().body(repository.findAll(), Whisky.class))
                        .GET("/{id}", req ->
                                Mono.fromCallable(() -> UUID.fromString(req.pathVariable("id")))
                                        .flatMap(id -> repository.findById(id)
                                                .switchIfEmpty(Mono.error(new ElementNotFoundException(id))))
                                        .flatMap(ServerResponse.ok()::bodyValue)
                        )
                )
                .build().filter((req, next) -> next.handle(req)
                        .onErrorResume(ElementNotFoundException.class, ex -> {
                            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
                            pd.setProperty("id", ex.getId());
                            pd.setType(URI.create("http://localhost:8080/problems/post-not-found"));

                            return ServerResponse.status(HttpStatus.NOT_FOUND)
                                    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                                    .bodyValue(pd);
                        }));

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
