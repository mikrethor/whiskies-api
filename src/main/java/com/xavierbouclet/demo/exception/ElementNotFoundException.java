package com.xavierbouclet.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {


    private UUID id;
    public PostNotFoundException(UUID id) {
        this.id=id;
    }
    public UUID getId() {
        return id;
    }
}
