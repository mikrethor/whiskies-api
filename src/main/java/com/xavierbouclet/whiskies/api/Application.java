package com.xavierbouclet.whiskies.api;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.server.event.ServerStartupEvent;

public class Application implements ApplicationEventListener<ServerStartupEvent> {

    public static void main(String[] args) {
        System.out.println("Starting ...");
        Micronaut.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        System.out.println("App has started ...");

    }
}