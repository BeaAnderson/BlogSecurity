package com.fdmgroup.springauth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	@Column(unique=true)
	private String username;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="role_id")}
			)
	private Set<Role> authorities;
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="user")
	private List<Blog> blogs = new ArrayList<>();
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="user")
	private List<Comment> comments = new ArrayList<>();
	
	
	
	public List<Blog> getBlogs() {
		return blogs;
	}



	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public ApplicationUser() {
		super();
		this.authorities = new HashSet<Role>();
	}
	
	

	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}



	public ApplicationUser(int userId, String username, String password, Set<Role> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
