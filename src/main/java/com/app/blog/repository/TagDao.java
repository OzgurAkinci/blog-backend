package com.app.blog.repository;


import com.app.blog.domain.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TagDao extends JpaRepository<Tag, Integer>{
	@Query("SELECT count(id) FROM Category")
	public Long getTotalCount() throws Exception;

	@Query("SELECT e FROM Tag e WHERE e.tagName like %:tagName%")
	public List<Tag> findTagByFilters(@Param("tagName") String tagName, Pageable pageable) throws Exception;
}	

