package com.xavierbouclet.whiskies.api.service;

import com.xavierbouclet.whiskies.api.model.Whisky;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface WhiskyService {

    @GetExchange("/whiskies")
    Flux<Whisky> loadAll();
}
