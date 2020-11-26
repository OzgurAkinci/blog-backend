package com.app.blog.controller_public;

import com.app.blog.domain.CustomListCollection;
import com.app.blog.domain.Post;
import com.app.blog.repository.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/public/post")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class PublicSPostController {
	@Autowired
	protected PostDao postDao;
	
	@GetMapping("/get/{id}")
	public Post get(@PathVariable("id") Integer id) {
		return postDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<Post> list() {
		return postDao.findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<Post> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
                                               @RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<Post> c = new CustomListCollection<Post>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(postDao.findPostByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(postDao.getTotalCount());
		return c;
	}
}
