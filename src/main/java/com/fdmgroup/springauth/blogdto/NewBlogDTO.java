package com.fdmgroup.springauth.blogdto;

import com.fdmgroup.springauth.model.ApplicationUser;


public class NewBlogDTO {
	
	private String title;
	private String body;
	private ApplicationUser user;
	public NewBlogDTO() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ApplicationUser getUser() {
		return user;
	}
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
	
}
