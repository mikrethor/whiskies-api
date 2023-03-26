package com.xavierbouclet.whiskies.api.configuration;

import com.xavierbouclet.whiskies.api.exception.ElementNotFoundException;
import com.xavierbouclet.whiskies.api.handler.ErrorHandler;
import com.xavierbouclet.whiskies.api.handler.WhiskyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> whiskiesRouter(WhiskyHandler whiskyHandler, ErrorHandler errorHandler) {
        return route()
                .GET("/api/whiskies", whiskyHandler::getWhiskies)
                .GET("/api/whiskies/{id}", whiskyHandler::getAWhiskyById)
                .onError(ElementNotFoundException.class, errorHandler::elementNotFoundHandler)
                .build();
    }


}
