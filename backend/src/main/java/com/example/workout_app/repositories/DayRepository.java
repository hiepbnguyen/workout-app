package com.example.workout_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    
}
