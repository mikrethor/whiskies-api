package com.xavierbouclet.whiskies.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ElementNotFoundException.class)
    public ProblemDetail handlePostNotFoundException(ElementNotFoundException exception) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Whisky %s not found".formatted(exception.getId()));
        problemDetail.setType(URI.create("http://localhost:8080/problems/post-not-found"));
        problemDetail.setTitle("Out of Stock");
        problemDetail.setProperty("id", exception.getId());
        return problemDetail;
    }
}
