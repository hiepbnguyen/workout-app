package com.example.workout_app.service;

import java.util.List;

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
        if (routine.getAccount().getId() != account.getId() || workout.getAccount().getId() != account.getId()){
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

    @Transactional
    public Routine addWorkoutsToRoutine(Long routineId, List<Long> workoutIds){
        Account account = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        
        // Pulls routine from database
        Routine routine = routineRepository.findById(routineId)
            .orElseThrow(() -> new Error("Routine not found"));
        
        // Check if routine is owned by user
        if (routine.getAccount().getId() != account.getId()){
            throw new Error("Account does not own routine.");
        }
        
        // Pulls workouts from database
        List<Workout> workouts = workoutRepository.findAllById(workoutIds);

        // Check if all workouts exists in database
        if (workouts.size() != workoutIds.size()){
            throw new Error("One or more of the workout ids does not exist.");
        }

        // Check if all workouts are owned by the account
        for (Workout workout : workouts) {
            if (workout.getAccount().getId() != account.getId()){
                throw new Error("Account does not own workout.");
            }
        }

        // Create new RoutineWorkouts for linkage
        for (Workout workout : workouts) {
            System.out.println("BANANANA\n\n\nn\n\n\n");
            RoutineWorkout routineWorkout = new RoutineWorkout(routine, workout, routine.getRoutineWorkouts().size() + 1);
            routine.getRoutineWorkouts().add(routineWorkout);
            workout.getRoutineWorkouts().add(routineWorkout);
            routineWorkoutRepository.save(routineWorkout);
        }
        
        return routine;
    }
}