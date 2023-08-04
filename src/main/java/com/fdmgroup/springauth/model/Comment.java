package com.fdmgroup.springauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Comment_Table")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="commentgen")
	@SequenceGenerator(name="commentgen", sequenceName="comment_id_seq", allocationSize=1)
	private long id;
	private String body;
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "comment_blog",
	joinColumns = {@JoinColumn(name="comment_id")},
	inverseJoinColumns = {@JoinColumn(name="blog_id")})
	private Blog blog;
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "comment_user",
	joinColumns = {@JoinColumn(name="comment_id")},
	inverseJoinColumns = {@JoinColumn(name="user_id")})
	private ApplicationUser user;
	
	public Comment(long id, String body, Blog blog, ApplicationUser user) {
		super();
		this.id = id;
		this.body = body;
		this.blog = blog;
		this.user = user;
	}
	
	public Comment() {
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBlogTitle() {
		if (blog != null) {
			return blog.getTitle();
		}
		return null;
	}
	@JsonIgnore
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public String getUsername() {
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}
	@JsonIgnore
	public ApplicationUser getUser() {
		return user;
	}
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
	
}
