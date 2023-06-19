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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/whiskies")
public class WhiskyController {

    private final WhiskyRepository postRepository;

    public WhiskyController(WhiskyRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Whisky> findAll() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Whisky findById(@PathVariable("id") UUID id) {
        return postRepository.findById(id).orElseThrow(()->new ElementNotFoundException(id));
    }
}
