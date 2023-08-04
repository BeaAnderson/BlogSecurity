package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.ApplicationUser;

import com.fdmgroup.springauth.repository.UserRepository;



@Service
public class UserServiceBlog {
	private final UserRepository userRepo;

	public UserServiceBlog(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<ApplicationUser> listAllUsers(){
		return userRepo.findAll();
	}
	
	public ApplicationUser addUser(ApplicationUser user) {
		return userRepo.save(user);
	}

	public ApplicationUser findById(int id) {
		Optional<ApplicationUser> optUser = userRepo.findById(id);
		if (optUser.isEmpty()) {}
		return optUser.get();
	}

	public void deleteByUsername(int id) {
		if (!userRepo.existsById(id)) {}
		userRepo.deleteById(id);
	}

	public ApplicationUser updateUser(ApplicationUser user, int id) {
		Optional<ApplicationUser> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			return userRepo.save(user);
		} else {
			
		}
		return null;
		
	}
}
