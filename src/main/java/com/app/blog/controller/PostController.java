package com.app.blog.controller;

import com.app.blog.domain.CustomListCollection;
import com.app.blog.domain.Post;
import com.app.blog.repository.AppDao;
import com.app.blog.repository.PostDao;
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
public class PostController {
	@Autowired
	protected AppDao appDao;
	
	@GetMapping("/get/{id}")
	public Post get(@PathVariable("id") Integer id) {
		return appDao.getPostDao().getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<Post> list() {
		return appDao.getPostDao().findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<Post> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
											   @RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<Post> c = new CustomListCollection<Post>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(appDao.getPostDao().findPostByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(appDao.getPostDao().getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody Post model) throws Exception {
		Post u = appDao.getPostDao().save(model);
		if(u != null) {
			return new ResponseEntity<Post>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Post> deleteById(@PathVariable("id") Integer id) {
		try {
			appDao.getPostDao().deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Post> (HttpStatus.ACCEPTED);
	}
}
