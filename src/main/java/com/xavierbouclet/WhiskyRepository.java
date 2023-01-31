package com.xavierbouclet;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
@Startup
@ApplicationScoped
public class WhiskyRepository implements PanacheRepositoryBase<Whisky, UUID> {
}
