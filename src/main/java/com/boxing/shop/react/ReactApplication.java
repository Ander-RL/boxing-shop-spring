package com.boxing.shop.react;

import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Role;
import com.boxing.shop.react.repository.IRoleRepository;
import com.boxing.shop.react.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactApplication.class, args);
	}

	@Bean
	CommandLineRunner run(IRoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role(1, "ADMIN"));
			roleRepository.save(new Role(2, "USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1L, "ander", passwordEncode.encode("123"), roles);

			userRepository.save(admin);
		};
	}

}
