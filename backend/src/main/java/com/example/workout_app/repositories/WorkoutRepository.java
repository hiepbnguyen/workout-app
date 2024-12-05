package com.example.workout_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.defaults.Workout;
import java.util.List;
import java.util.Optional;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{
    Optional<Workout> findById(Long id);
}
