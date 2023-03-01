package com.xavierbouclet.whiskies.api.service

import com.xavierbouclet.whiskies.api.model.Whisky
import org.springframework.web.service.annotation.GetExchange

interface WhiskyService {
    @GetExchange("/whiskies")
    fun loadAll(): List<Whisky>
}