package com.xavierbouclet.demo.repository;

import com.xavierbouclet.demo.model.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer>, ListCrudRepository<Post, Integer> {
}
