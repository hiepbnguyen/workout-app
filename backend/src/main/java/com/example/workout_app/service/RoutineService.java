package com.example.workout_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.Routine;
import com.example.workout_app.models.defaults.RoutineWorkout;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.repositories.RoutineRepository;
import com.example.workout_app.repositories.RoutineWorkoutRepository;
import com.example.workout_app.repositories.WorkoutRepository;
import com.example.workout_app.security.SecurityUser;

@Service
public class RoutineService {
    @Autowired
    private final RoutineRepository routineRepository;
    private final WorkoutRepository workoutRepository;
    private final RoutineWorkoutRepository routineWorkoutRepository;
    
    public RoutineService(RoutineRepository routineRepository, WorkoutRepository workoutRepository, RoutineWorkoutRepository routineWorkoutRepository) {
        this.routineRepository = routineRepository;
        this.workoutRepository = workoutRepository;
        this.routineWorkoutRepository = routineWorkoutRepository;
    }

    // Saves a routine to the database
    public Routine createRoutine(Routine routine) {
        return routineRepository.save(routine);
    }

    @Transactional
    public Routine addWorkoutToRoutine(Long routineId, Long workoutId) {
        // Retrieves Account from Security Context Holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        Account account = securityUser.getAccount();

        // Retrieves workout and routine from database
        Routine routine = routineRepository.findById(routineId)
            .orElseThrow(() -> new Error("Routine not found"));
        Workout workout = workoutRepository.findById(workoutId)
            .orElseThrow(() -> new Error("Workout not found"));

        // Check if the workout and routine are under the user's account
        if (routine.getAccount() != account || workout.getAccount() != account){
            throw new Error("Account does not own workout and/or routine.");
        }
        
        // Create a routine workout
        RoutineWorkout routineWorkout = new RoutineWorkout(routine, workout, workout.getRoutineWorkouts().size() + 1);

        // Updates inverse relationships
        routine.getRoutineWorkouts().add(routineWorkout);
        workout.getRoutineWorkouts().add(routineWorkout);

        // Saves routine workout
        routineWorkoutRepository.save(routineWorkout);

        return routine;
    }
}