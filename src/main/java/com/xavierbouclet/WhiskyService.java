package com.xavierbouclet;

import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

@Startup
@Path("/whiskies")
@RegisterRestClient(configKey = "whiskies-api")
public interface WhiskyService {

    @GET
    Set<Whisky> getAll();
}
