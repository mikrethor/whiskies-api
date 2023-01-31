package com.xavierbouclet.whiskies.api.controller;

import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller("/api/whiskies")
public class WhiskyController {

    private final WhiskyRepository whiskyRepository;

    public WhiskyController(WhiskyRepository whiskyRepository) {
        this.whiskyRepository=whiskyRepository;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    HttpResponse<?>  list() {
        return HttpResponse.status(HttpStatus.OK).body(StreamSupport.stream(whiskyRepository.findAll().spliterator(), false)
                .toList());

    }
    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    HttpResponse<?> findById(@PathVariable UUID id)  {
        return HttpResponse.status(HttpStatus.OK).body(whiskyRepository.findById(id));
    }
}
