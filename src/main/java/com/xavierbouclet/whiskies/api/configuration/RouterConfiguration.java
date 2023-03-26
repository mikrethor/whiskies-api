package com.xavierbouclet.whiskies.api.configuration;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration(proxyBeanMethods = false)
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> whiskiesRouter(WhiskyRepository wr) {
        return route()
                .GET("/api/whiskies", req -> ok().body(wr.findAll()))
                .GET("/api/whiskies/{id}", req -> {
                    var id = UUID.fromString(req.pathVariable("id"));
                    return ok().body(wr.findById(id).orElseThrow(() -> new ElementNotFoundException(id)));
                })
                .onError(ElementNotFoundException.class,
                        (e, req) -> {
                            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
                            problemDetail.setProperty("id", ((ElementNotFoundException) e).getId());
                            try {
                                problemDetail.setType(new URI("http://localhost:8080/problems/post-not-found"));
                                problemDetail.setInstance(new URI(req.requestPath().toString()));
                            } catch (URISyntaxException ex) {
                                throw new RuntimeException(ex);
                            }

                            return EntityResponse.fromObject(problemDetail)
                                    .status(HttpStatus.NOT_FOUND)
                                    .build();
                        })
                .build();
    }
}
