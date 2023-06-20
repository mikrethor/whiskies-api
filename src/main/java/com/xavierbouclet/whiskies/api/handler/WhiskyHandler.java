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

    private final WhiskyRepository postRepository;

    public WhiskyHandler(WhiskyRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ServerResponse findAll(ServerRequest serverRequest) {
        return  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(postRepository.findAll());
    }

    public ServerResponse findById(ServerRequest serverRequest) {
        UUID id=UUID.fromString(serverRequest.pathVariable("id"));
        return ServerResponse.ok().body(postRepository.findById(id).orElseThrow(()->new ElementNotFoundException(id)));
    }
}
