package com.app.blog.controller;

import com.app.blog.domain.Tag;
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

@RequestMapping("/tag")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class TagController {
	@Autowired
	protected AppDao appDao;
	
	@GetMapping("/get/{id}")
	public Tag get(@PathVariable("id") Integer id) {
		return appDao.getTagDao().getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<Tag> list() {
		return appDao.getTagDao().findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<Tag> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
											   @RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<Tag> c = new CustomListCollection<Tag>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(appDao.getTagDao().findTagByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(appDao.getTagDao().getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody Tag model) throws Exception {
		Tag u = appDao.getTagDao().save(model);
		if(u != null) {
			return new ResponseEntity<Tag>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Tag> deleteById(@PathVariable("id") Integer id) {
		try {
			appDao.getTagDao().deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Tag> (HttpStatus.ACCEPTED);
	}

	@GetMapping("/getTotalCount")
	public Long getTotalCount() throws Exception {
		return appDao.getTagDao().getTotalCount();
	}
}
