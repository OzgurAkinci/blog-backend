package com.app.blog.repository;


import com.app.blog.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryDao extends JpaRepository<Category, Integer>{
	@Query("SELECT count(id) FROM Category")
	Long getTotalCount() throws Exception;

	@Query("SELECT e FROM Category e WHERE e.categoryName like %:categoryName%")
	List<Category> findCategoryByFilters(@Param("categoryName") String categoryName, Pageable pageable) throws Exception;
}	

