package com.example.workout_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workout_app.models.Routine;
import com.example.workout_app.repositories.RoutineRepository;

@Service
public class RoutineService {
    @Autowired
    private final RoutineRepository routineRepository;
    
    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    // Saves a routine to the database
    public Routine createRoutine(Routine routine) {
        return routineRepository.save(routine);
    }
}
