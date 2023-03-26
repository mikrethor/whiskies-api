package com.xavierbouclet.whiskies.api.handler;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.UUID;

@Component
public class WhiskyHandler {

    private final WhiskyRepository whiskyRepository;

    public WhiskyHandler(WhiskyRepository whiskyRepository) {
        this.whiskyRepository = whiskyRepository;
    }

    public ServerResponse getWhiskies(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(whiskyRepository.findAll());
    }

    public ServerResponse getAWhiskyById(ServerRequest serverRequest) {
        var id = UUID.fromString(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .body(whiskyRepository.findById(id)
                        .orElseThrow(() -> new ElementNotFoundException(id)));
    }
}
