package com.xavierbouclet.whiskies.api.controller

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException
import com.xavierbouclet.whiskies.api.model.Whisky
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/whiskies")
class WhiskyController(private val postRepository: WhiskyRepository) {
    @GetMapping
    fun findAll(): List<Whisky> {
        return postRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID): Whisky {
        return postRepository.findByIdOrNull(id) ?: throw ElementNotFoundException(id,"Not Found")
    }
}