package com.example.workout_app.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.workout_app.models.Day;
import com.example.workout_app.models.Player;
import com.example.workout_app.repositories.PlayerRepository;

@Configuration
public class PlayerConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository){
        return args -> {
            List<Day> alexDays = new ArrayList<Day>();
            alexDays.add(new Day());

            Player alex = new Player(
                "Alex",
                "alex@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 4),
                null
            );
            Player john = new Player(
                "Mariam",
                "mariam@gmail.com",
                LocalDate.of(2002, Month.AUGUST, 5),
                null
            );

            playerRepository.saveAll(
                List.of(alex, john)
            );
        };
    }
}
