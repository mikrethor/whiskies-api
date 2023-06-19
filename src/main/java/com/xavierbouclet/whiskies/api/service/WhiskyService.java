package com.xavierbouclet.whiskies.api.service;

import com.xavierbouclet.whiskies.api.model.Whisky;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "whiskies-client", url = "${client.whisky.service.url}")
public interface WhiskyService {

    @RequestMapping(method = RequestMethod.GET, value = "/whiskies")
    List<Whisky> loadAll();
}
