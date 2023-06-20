package com.xavierbouclet.whiskies.api.service;

import com.xavierbouclet.whiskies.api.model.Whisky;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface WhiskyService {

    @GetExchange("/whiskies")
    List<Whisky> loadAll();
}
