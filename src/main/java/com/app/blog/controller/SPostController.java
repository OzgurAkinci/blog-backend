package com.app.blog.controller;

import com.app.blog.domain.CustomListCollection;
import com.app.blog.domain.SPost;
import com.app.blog.repository.SPostDao;
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

@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class SPostController {
	@Autowired
	protected SPostDao postDao;
	
	@GetMapping("/get/{id}")
	public SPost get(@PathVariable("id") Integer id) {
		return postDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<SPost> list() {
		return postDao.findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<SPost> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
												@RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<SPost> c = new CustomListCollection<SPost>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(postDao.findPostByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(postDao.getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody SPost model) throws Exception {
		SPost u = postDao.save(model);
		if(u != null) {
			return new ResponseEntity<SPost>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SPost> deleteById(@PathVariable("id") Integer id) {
		try {
			postDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SPost> (HttpStatus.ACCEPTED);
	}
}
