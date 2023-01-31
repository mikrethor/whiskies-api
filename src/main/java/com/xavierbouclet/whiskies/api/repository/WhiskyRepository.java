package com.xavierbouclet.whiskies.api.repository;

import com.xavierbouclet.whiskies.api.model.Whisky;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface WhiskyRepository extends CrudRepository<Whisky, UUID> {
}
