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
import com.fdmgroup.springauth.repository.RoleRepository;
import com.fdmgroup.springauth.repository.UserRepository;

@SpringBootApplication
public class SpringauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			
			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);
			
			userRepository.save(admin);
		};
	}
}
