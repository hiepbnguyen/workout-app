package com.example.workout_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.Routine;
import java.util.Optional;


@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Optional<Routine> findById(Long id);
}