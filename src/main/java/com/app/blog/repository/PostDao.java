package com.app.blog.repository;


import com.app.blog.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PostDao extends JpaRepository<Post, Integer>{
	@Query("SELECT count(id) FROM Post")
	Long getTotalCount() throws Exception;

	@Query("SELECT e FROM Post e WHERE e.postTitle like %:postTitle%")
	List<Post> findPostByFilters(@Param("postTitle") String postTitle, Pageable pageable) throws Exception;
}	

