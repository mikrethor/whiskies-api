package com.xavierbouclet.whiskies.api.service;

import com.xavierbouclet.whiskies.api.model.Whisky;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import static io.micronaut.http.HttpHeaders.ACCEPT;

@Client("http://localhost:3000/whiskies")
@Header(name = ACCEPT, value = "application/json")
public interface WhiskyService {

    @Get
    List<Whisky> loadAll();
}
