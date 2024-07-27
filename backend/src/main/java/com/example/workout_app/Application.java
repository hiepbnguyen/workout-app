package com.example.workout_app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.workout_app.models.Role;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.repositories.RoleRepository;
import com.example.workout_app.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository users, RoleRepository roles, PasswordEncoder encoder) {
		return args -> {
			Role userRole = new Role("ROLE_USER");
			Role adminRole = new Role("ROLE_ADMIN");

			roles.save(userRole);
			roles.save(adminRole);

			// users.save(new UserEntity(
			// 	"hiep@gmail.com",
			// 	encoder.encode("banana"),
			// 	"Hiep",
			// 	LocalDate.of(2000, 5, 5),
			// 	Arrays.asList(
			// 		new Role("USER"),
			// 		new Role("ADMIN")
			// 	)
			// ));
			
			// users.save(new UserEntity(
			// 	"apple@gmail.com",
			// 	encoder.encode("banana"),
			// 	"apple",
			// 	LocalDate.of(2000, 5, 5),
			// 	Arrays.asList(
			// 		new Role("USER"),
			// 		new Role("ADMIN")
			// 	)
			// ));
		};
	}

	@GetMapping("/")
	public List<String> test() {
		return List.of("test", "test");
	}
}