package com.app.blog.repository;


import com.app.blog.domain.SPost;
import com.app.blog.domain.SRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SPostDao extends JpaRepository<SPost, Integer>{
	@Query("SELECT count(id) FROM SPost")
	public Long getTotalCount() throws Exception;

	@Query("SELECT e FROM SPost e WHERE e.postTitle like %:postTitle%")
	public List<SPost> findPostByFilters(@Param("postTitle") String postTitle, Pageable pageable) throws Exception;
}	

