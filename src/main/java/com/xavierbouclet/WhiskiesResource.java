package com.xavierbouclet;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/api/whiskies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WhiskiesResource {

    private WhiskyRepository repository;

    @Inject
    public WhiskiesResource( WhiskyRepository repository) {

        this.repository=repository;
    }

    @GET
    public Uni<List<Whisky>> list(){
        return repository.findAll().list();
    }

    @GET
    @Path("{id}")
    public Uni<Whisky>  findById(@PathParam("id") UUID id){
        return repository.findById(id);
    }
}