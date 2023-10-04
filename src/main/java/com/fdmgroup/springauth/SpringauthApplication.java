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
			Role userRole = roleRepository.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			
			Set<Role> roles2 = new HashSet<>();
			roles2.add(userRole);
			
			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);
			ApplicationUser user = new ApplicationUser(2, "bee", passwordEncoder.encode("password"), roles2);
			
			Blog blog = new Blog(1, "blog title", """
					Vivamus congue ac enim quis aliquam. Sed dui metus, rhoncus vitae magna eu, convallis feugiat ante. Duis lobortis est libero, et tempus ante efficitur ut. Donec ultrices dignissim est, at auctor nibh varius quis. Vestibulum non risus sed nisi blandit semper venenatis ac dui. Etiam ut magna pellentesque, euismod augue a, malesuada ipsum. Etiam vel nisi est. Proin malesuada felis dolor, a tincidunt diam fringilla porta. Nunc sed elit urna. Sed nisl tortor, malesuada sed nisi at, ultricies accumsan magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent pharetra odio id mauris dignissim, ac pulvinar mauris placerat.
					In pulvinar, nisl at cursus scelerisque, ligula nunc congue erat, quis venenatis odio nisl auctor odio. Donec dapibus commodo tristique. Ut sit amet nisl et tortor accumsan cursus. Morbi iaculis tellus nec eros faucibus dictum. Duis tempus sem eget enim consectetur, vel egestas felis condimentum. Vestibulum consequat sapien id diam iaculis consequat. Duis auctor nisi ut molestie tristique. In hac habitasse platea dictumst. Sed dictum leo lacus, ut congue erat hendrerit commodo. Cras bibendum justo ligula. Nullam pulvinar molestie ante et maximus. Nunc scelerisque efficitur mauris sit amet tristique. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur efficitur sit amet leo sit amet viverra.
					""", admin, "https://worldanimalfoundation.org/wp-content/uploads/2022/09/How-To-Make-a-Leather-Dog-Collar.jpg");
			Blog blog2 = new Blog(2, "blog 2 title", """
					Vivamus nulla nibh, varius sit amet facilisis ut, vulputate et quam. Sed sed pretium sapien, ac fermentum erat. Phasellus gravida fringilla tellus, in vulputate velit. Proin pharetra bibendum hendrerit. Praesent quis scelerisque lacus. Sed nec ultricies justo, ut egestas est. Fusce sit amet libero vulputate, lacinia arcu at, viverra massa. Pellentesque fringilla nisl ac risus placerat, id cursus felis iaculis. Phasellus mollis metus mi, ut volutpat enim tincidunt at.
					Nam auctor massa cursus, volutpat odio et, aliquet ligula. Nunc eleifend magna in augue elementum consequat. Duis consequat, ipsum eu congue egestas, justo erat gravida enim, placerat volutpat nibh dolor vitae enim. Quisque nec interdum felis. Nam facilisis orci elit, et fermentum odio suscipit in. Mauris dapibus interdum nisl vitae viverra. Donec non pretium felis, eget rhoncus nisi. Duis eu arcu ut nisi maximus sagittis eu non purus. Etiam vestibulum sagittis egestas. Suspendisse nec ullamcorper eros, vitae luctus mauris. Proin facilisis ipsum ullamcorper lacus consequat commodo. Nam faucibus pellentesque lectus, eu molestie metus ultricies non. Mauris vitae auctor mi, non congue sapien. Nulla facilisi. Vestibulum id massa id nisi rutrum sollicitudin in sit amet nisl. Maecenas maximus neque eget leo dignissim, sed tincidunt nisi congue.
					""", admin, "https://worldanimalfoundation.org/wp-content/uploads/2022/09/How-To-Make-a-Leather-Dog-Collar.jpg");
			Blog blog3 = new Blog(3, "blog 3 title", "blog body", admin, "https://worldanimalfoundation.org/wp-content/uploads/2022/09/How-To-Make-a-Leather-Dog-Collar.jpg");
			Blog blog4 = new Blog(4, "blog 4 title", "blog body", admin, "https://worldanimalfoundation.org/wp-content/uploads/2022/09/How-To-Make-a-Leather-Dog-Collar.jpg");
			Blog blog5 = new Blog(5, "blog 5 title", "blog body", admin, "https://worldanimalfoundation.org/wp-content/uploads/2022/09/How-To-Make-a-Leather-Dog-Collar.jpg");
			
			userRepository.save(admin);
			userRepository.save(user);
			blogRepository.save(blog);
			blogRepository.save(blog2);
			blogRepository.save(blog3);
			blogRepository.save(blog4);
			blogRepository.save(blog5);
		};
	}
}
