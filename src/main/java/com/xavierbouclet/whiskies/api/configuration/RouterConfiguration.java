package com.xavierbouclet.whiskies.api.configuration;


import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.handler.WhiskyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.function.BiFunction;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> whiskiesRouter(WhiskyHandler whiskyHandler) {
        return route()
                .GET("/api/whiskies", whiskyHandler::findAll)
                .GET("/api/whiskies/{id}",whiskyHandler::findById)
                .onError(ElementNotFoundException.class,
                        error())
                .build();
    }

    private static BiFunction<Throwable, ServerRequest, ServerResponse> error() {
        return (e, req) -> {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
            problemDetail.setProperty("id", ((ElementNotFoundException) e).getId());
            problemDetail.setType(URI.create("http://localhost:8080/problems/post-not-found"));
            problemDetail.setInstance(URI.create(req.requestPath().toString()));
            problemDetail.setTitle("Out of Stock");

            return EntityResponse.fromObject(problemDetail)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        };
    }
}


