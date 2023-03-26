package com.xavierbouclet.whiskies.api.handler;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ErrorHandler {

    public ServerResponse elementNotFoundHandler(Throwable e, ServerRequest serverRequest) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("id", ((ElementNotFoundException) e).getId());
        try {
            problemDetail.setType(new URI("http://localhost:8080/problems/post-not-found"));
            problemDetail.setInstance(new URI(serverRequest.requestPath().toString()));
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

        return EntityResponse.fromObject(problemDetail)
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
