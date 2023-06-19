package com.xavierbouclet.whiskies.api.repository;

import com.xavierbouclet.whiskies.api.model.Whisky;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface WhiskyRepository extends PagingAndSortingRepository<Whisky, UUID>, CrudRepository<Whisky, UUID> {
}
