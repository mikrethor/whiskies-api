package com.xavierbouclet.whiskies.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;

import java.net.URI;

import static org.zalando.problem.Status.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ElementNotFoundException.class)
    public Problem handlePostNotFoundException(ElementNotFoundException exception) {
       return Problem.builder()

                .withType(URI.create("http://localhost:8080/problems/post-not-found"))
                .withTitle("Out of Stock")
                .withStatus(NOT_FOUND)
                .withDetail(String.format("Whisky %s not found", exception.getId()))
                .with("id", exception.getId()).build();
    }
}
