package com.xavierbouclet.whiskies.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {


    private final UUID id;
    public ElementNotFoundException(UUID id) {
        this.id=id;
    }
    public UUID getId() {
        return id;
    }
}
