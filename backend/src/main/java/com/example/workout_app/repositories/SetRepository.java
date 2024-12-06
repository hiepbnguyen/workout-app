package com.example.workout_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.workout_app.models.defaults.WorkSet;

public interface SetRepository extends JpaRepository<WorkSet, Long> {
    
}
