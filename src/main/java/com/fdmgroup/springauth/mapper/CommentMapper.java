package com.fdmgroup.springauth.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fdmgroup.springauth.blogdto.NewCommentDTO;
import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.Blog;
import com.fdmgroup.springauth.model.Comment;
import com.fdmgroup.springauth.repository.BlogRepository;
import com.fdmgroup.springauth.repository.UserRepository;
import com.fdmgroup.springauth.repository.UserRepositoryBlog;



@Component
public class CommentMapper {
	private BlogRepository blogRepo;
	private UserRepository userRepo;

	public CommentMapper(BlogRepository blogRepo, UserRepository userRepo) {
		super();
		this.blogRepo = blogRepo;
		this.userRepo = userRepo;
	}
	
	public Comment toEntity(NewCommentDTO commentDto) {
		Comment newComment = new Comment();
		
		newComment.setBody(commentDto.getBody());
		
		Blog blogReturn;
		Blog blog1 = commentDto.getBlog();
		Optional<Blog> optBlog = blogRepo.findById(blog1.getId());
		if (optBlog.isPresent()) {
			blogReturn = optBlog.get();
		} else {
			blogReturn = commentDto.getBlog();
		}
		newComment.setBlog(blogReturn);
		
		ApplicationUser userReturn;
		ApplicationUser user1 = commentDto.getUser();
		Optional<ApplicationUser> optUser = userRepo.findById(user1.getUserId());
		if (optUser.isPresent()) {
			userReturn = optUser.get();
		} else {
			userReturn = commentDto.getUser();
		}
		newComment.setUser(userReturn);
		
		return newComment;
	}
}
