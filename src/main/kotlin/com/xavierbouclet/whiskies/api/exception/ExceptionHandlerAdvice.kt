package com.xavierbouclet.whiskies.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.URI
import java.net.URISyntaxException

@RestControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(ElementNotFoundException::class)
    @Throws(URISyntaxException::class)
    fun handlePostNotFoundException(exception: ElementNotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.message!!)
        problemDetail.setProperty("id", exception.id)
        problemDetail.type = URI("http://localhost:8080/problems/post-not-found")
        return problemDetail
    }
}