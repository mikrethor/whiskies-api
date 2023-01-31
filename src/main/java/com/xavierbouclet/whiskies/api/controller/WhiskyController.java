package com.xavierbouclet.whiskies.api.controller;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.model.Whisky;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/whiskies")
public class WhiskyController {

    private final WhiskyRepository postRepository;

    public WhiskyController(WhiskyRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Whisky> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Whisky findById(@PathVariable("id") UUID id) {
        return postRepository.findById(id).orElseThrow(()->new ElementNotFoundException(id));
    }
}
