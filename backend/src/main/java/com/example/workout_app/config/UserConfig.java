package com.example.workout_app.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.workout_app.models.Day;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.repositories.UserRepository;

@Configuration
public class UserConfig {
    
    // Adds two users by default
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            List<Day> alexDays = new ArrayList<Day>();
            alexDays.add(new Day());

            UserEntity alex = new UserEntity(
                "Alex",
                "alex@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 4),
                null
            );
            UserEntity john = new UserEntity(
                "Mariam",
                "mariam@gmail.com",
                LocalDate.of(2002, Month.AUGUST, 5),
                null
            );

            userRepository.saveAll(
                List.of(alex, john)
            );
        };
    }
}
