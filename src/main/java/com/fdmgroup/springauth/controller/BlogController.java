package com.fdmgroup.springauth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.blogdto.NewBlogDTO;
import com.fdmgroup.springauth.mapper.BlogMapper;
import com.fdmgroup.springauth.model.Blog;
import com.fdmgroup.springauth.service.BlogService;

@RestController
@RequestMapping("api/v1/blogs")
public class BlogController {
	
	public final BlogService blogService;
	private final BlogMapper blogMapper;

	public BlogController(BlogService blogService, BlogMapper blogMapper) {
		super();
		this.blogService = blogService;
		this.blogMapper = blogMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<Blog>> getAllBlogs(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(blogService.listAllBlogs());
	}
	
	@PostMapping
	public ResponseEntity<Blog> addBlog(@RequestBody NewBlogDTO blogDto){
		Blog blog = blogService.addBlog(blogMapper.toEntity(blogDto));
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(blog.getId())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(blog);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable long id){
		return ResponseEntity.ok(blogService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBlog(@PathVariable long id){
		blogService.deleteById(id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Blog> updateBlog(@RequestBody NewBlogDTO blogDto, @PathVariable long id){
		return ResponseEntity
				.ok(blogService.updateBlog(blogMapper.toEntity(blogDto), id));
	}
}
