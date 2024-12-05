package com.example.workout_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.defaults.RoutineWorkout;

@Repository
public interface RoutineWorkoutRepository extends JpaRepository<RoutineWorkout, Long> {
    
}
