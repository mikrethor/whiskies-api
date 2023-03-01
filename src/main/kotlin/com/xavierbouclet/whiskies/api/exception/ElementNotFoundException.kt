package com.xavierbouclet.whiskies.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class ElementNotFoundException(val id: UUID, override val message:String) : RuntimeException(message)