package com.fdmgroup.springauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.model.ApplicationUser;

import com.fdmgroup.springauth.service.UserService;
import com.fdmgroup.springauth.service.UserServiceBlog;

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


@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	public final UserServiceBlog userService;

	public UserController(UserServiceBlog userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<ApplicationUser>> getAllUsers(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.listAllUsers());	
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<ApplicationUser> getUser(@PathVariable int id){
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@DeleteMapping("/user/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id){
		userService.deleteByUsername(id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}

}
