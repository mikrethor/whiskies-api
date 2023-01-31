package com.xavierbouclet;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = Logger.getLogger(AppLifecycleBean.class);
    @RestClient
    private WhiskyService whiskyService;
    private WhiskyRepository whiskyRepository;

    @Inject
    public AppLifecycleBean(WhiskyRepository whiskyRepository) {
        this.whiskyRepository = whiskyRepository;
    }

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
        whiskyRepository.persist(whiskyService.getAll().stream().map(whisky -> new Whisky(UUID.nameUUIDFromBytes(whisky.bottle.getBytes()),
                whisky.bottle,
                whisky.price,
                whisky.rating,
                whisky.region))).await().indefinitely();

        LOGGER.info("Number of elements added : "+ whiskyRepository.count().await().indefinitely());
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }
}
