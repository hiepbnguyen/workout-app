package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.Routine;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.security.SecurityUser;
import com.example.workout_app.service.RoutineService;
import com.example.workout_app.service.WorkoutService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/v1/routine")
public class RoutineController {
    @Autowired
    private final RoutineService routineService;
    private final WorkoutService workoutService;

    public RoutineController(RoutineService routineService, WorkoutService workoutService) {
        this.routineService = routineService;
        this.workoutService = workoutService;
    }

    @PostMapping()
    public ResponseEntity<Routine> createRoutine(@RequestParam String name) {
        // Retrieves Account from Security Context Holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        Account account = securityUser.getAccount();

        // Creates a new Routine with a Name and Account
        Routine newRoutine = new Routine();
        newRoutine.setName(name);
        newRoutine.setAccount(account);

        // Saves routine to database
        routineService.createRoutine(newRoutine);
        System.out.println("New routine has been created. ID: " + newRoutine.getId());

        // Returns an ok response entity
        return ResponseEntity.ok(newRoutine);
    }

    // TODO: Verify that Workout and Routine are under the user's account (?)
    // Could skip ^ if we decide that mutliple users have access to same workout, but not plausible because
    // of logging for workout weights. Might be useful to have people share empty routine templates with
    // each other? Or maybe have routines posted on a user's account and other users can view routines and then
    // copy them for themselves.
    @PostMapping("/addworkout")
    public ResponseEntity<Routine> addWorkoutToRoutine(@RequestParam Long routineId, @RequestParam Long workoutId) {
        Routine routine = routineService.addWorkoutToRoutine(routineId, workoutId);

        return ResponseEntity.ok(routine);
    }

    @PostMapping("/workout")
    public ResponseEntity<Workout> createWorkout(@RequestBody String name) {
        Account account = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        
        Workout newWorkout = new Workout(account, name);

        workoutService.createWorkout(newWorkout);
        
        return ResponseEntity.ok(newWorkout);
    }
    

    // TODO: Implement copying routines
    @PostMapping("/copy")
    public ResponseEntity<Routine> copyRoutine(@RequestParam Long copyRoutineId) {
        // Retrieves Account from Security Context Holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        Account account = securityUser.getAccount();

        // Creates a new Routine with a Name and Account
        Routine newRoutine = new Routine();
        // newRoutine.setName(name);
        newRoutine.setAccount(account);

        // Saves routine to database
        routineService.createRoutine(newRoutine);
        System.out.println("New routine has been created. ID: " + newRoutine.getId());
        
        return ResponseEntity.ok(newRoutine);
    }
    
}