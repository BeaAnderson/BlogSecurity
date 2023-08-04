package com.fdmgroup.springauth.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fdmgroup.springauth.blogdto.NewBlogDTO;
import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.Blog;

import com.fdmgroup.springauth.repository.UserRepositoryBlog;



@Component
public class BlogMapper {
	private UserRepositoryBlog userRepo;

	public BlogMapper(UserRepositoryBlog userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public Blog toEntity(NewBlogDTO blogDto) {
		Blog newBlog= new Blog();
		
		newBlog.setTitle(blogDto.getTitle());
		newBlog.setBody(blogDto.getBody());
		
		ApplicationUser userReturn;
		ApplicationUser user1 = blogDto.getUser();
		Optional<ApplicationUser> optUser = userRepo.findById(user1.getUserId());
		if (optUser.isPresent()) {
			userReturn = optUser.get();
		} else {
			userReturn = blogDto.getUser();
		}
		newBlog.setUser(userReturn);
		
		return newBlog;
	}
}
