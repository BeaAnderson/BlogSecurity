package com.fdmgroup.springauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.ApplicationUser;




public interface UserRepositoryBlog extends JpaRepository<ApplicationUser, Integer>{

}
