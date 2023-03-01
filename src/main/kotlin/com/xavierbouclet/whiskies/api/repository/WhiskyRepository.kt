package com.xavierbouclet.whiskies.api.repository

import com.xavierbouclet.whiskies.api.model.Whisky
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.ListPagingAndSortingRepository
import java.util.*

interface WhiskyRepository : ListPagingAndSortingRepository<Whisky, UUID>, ListCrudRepository<Whisky, UUID>