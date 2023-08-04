package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Blog;
import com.fdmgroup.springauth.repository.BlogRepository;


@Service
public class BlogService {
	private final BlogRepository blogRepo;

	public BlogService(BlogRepository blogRepo) {
		this.blogRepo = blogRepo;
	}
	
	public List<Blog> listAllBlogs(){
		return blogRepo.findAll();
	}

	public Blog addBlog(Blog blog) {
		return blogRepo.save(blog);
	}

	public Blog findById(long id) {
		Optional<Blog> optBlog = blogRepo.findById(id);
		if (optBlog.isEmpty()) {}
		return optBlog.get();
	}

	public void deleteById(long id) {
		if (!blogRepo.existsById(id)) {}
		blogRepo.deleteById(id);
		
	}

	public Blog updateBlog(Blog blog, long id) {
		Optional<Blog> optBlog = blogRepo.findById(id);
		if (optBlog.isEmpty()) {}
		blog.setId(id);
		return blogRepo.save(blog);
	}
	
}
