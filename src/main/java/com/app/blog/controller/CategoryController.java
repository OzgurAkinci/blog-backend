package com.app.blog.controller;

import com.app.blog.domain.Category;
import com.app.blog.domain.CustomListCollection;
import com.app.blog.repository.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class CategoryController {
	@Autowired
	protected AppDao appDao;
	
	@GetMapping("/get/{id}")
	public Category get(@PathVariable("id") Integer id) {
		return appDao.getCategoryDao().getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<Category> list() {
		return appDao.getCategoryDao().findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<Category> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
											   @RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<Category> c = new CustomListCollection<Category>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(appDao.getCategoryDao().findCategoryByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(appDao.getCategoryDao().getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody Category model) throws Exception {
		Category u = appDao.getCategoryDao().save(model);
		if(u != null) {
			return new ResponseEntity<Category>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Category> deleteById(@PathVariable("id") Integer id) {
		try {
			appDao.getCategoryDao().deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Category> (HttpStatus.ACCEPTED);
	}
}
