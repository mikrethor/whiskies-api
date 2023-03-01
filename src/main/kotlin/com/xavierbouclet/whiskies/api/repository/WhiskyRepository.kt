package com.xavierbouclet.whiskies.api.repository;

import com.xavierbouclet.whiskies.api.model.Whisky;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface WhiskyRepository extends ListPagingAndSortingRepository<Whisky, UUID>, ListCrudRepository<Whisky, UUID> {
}
