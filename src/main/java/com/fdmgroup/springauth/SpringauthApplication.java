package com.fdmgroup.springauth;

import java.util.Set;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.springauth.model.Role;
import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.Blog;
import com.fdmgroup.springauth.repository.BlogRepository;
import com.fdmgroup.springauth.repository.RoleRepository;
import com.fdmgroup.springauth.repository.UserRepository;

@SpringBootApplication
public class SpringauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BlogRepository blogRepository) {
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			
			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);
			
			Blog blog = new Blog(1, "blog title", "blog body");
			Blog blog2 = new Blog(2, "blog 2 title", "blog body");
			Blog blog3 = new Blog(3, "blog 3 title", "blog body");
			Blog blog4 = new Blog(4, "blog 4 title", "blog body");
			Blog blog5 = new Blog(5, "blog 5 title", "blog body");
			
			userRepository.save(admin);
			blogRepository.save(blog);
			blogRepository.save(blog2);
			blogRepository.save(blog3);
			blogRepository.save(blog4);
			blogRepository.save(blog5);
		};
	}
}
