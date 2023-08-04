package com.fdmgroup.springauth.blogdto;

import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.Blog;


public class NewCommentDTO {
	private String body;
	private Blog blog;
	private ApplicationUser user;
	
	public NewCommentDTO() {
		super();
	}
	
	public ApplicationUser getUser() {
		return user;
	}
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
}
