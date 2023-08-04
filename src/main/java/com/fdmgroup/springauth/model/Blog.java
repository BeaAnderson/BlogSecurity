package com.fdmgroup.springauth.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@Entity
@Table(name="Blog_Table")
public class Blog {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bloggen")
	@SequenceGenerator(name="bloggen", sequenceName="blog_id_seq", allocationSize=1)
	private long id;
	private String title;
	private String body;
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "blog_user",
	joinColumns = {@JoinColumn(name="blog_id")},
	inverseJoinColumns = {@JoinColumn(name="user_id")})
	private ApplicationUser user;
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="blog")
	private List<Comment> comments = new ArrayList<>();
	
	public Blog(long id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public Blog() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
