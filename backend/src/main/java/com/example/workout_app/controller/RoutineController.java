package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.AddWorkoutsToRoutineDTO;
import com.example.workout_app.dto.CreateRoutineDTO;
import com.example.workout_app.dto.outgoing.RoutineDTO;
import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.Routine;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.security.SecurityUser;
import com.example.workout_app.service.RoutineService;
import com.example.workout_app.service.WorkoutService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/v1/routines")
public class RoutineController {
    @Autowired
    private final RoutineService routineService;
    private final WorkoutService workoutService;

    public RoutineController(RoutineService routineService, WorkoutService workoutService) {
        this.routineService = routineService;
        this.workoutService = workoutService;
    }

    @PostMapping()
    public ResponseEntity<Routine> createRoutine(@RequestBody CreateRoutineDTO dto) {
        // Retrieves Account from Security Context Holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        Account account = securityUser.getAccount();

        // Creates a new Routine with a Name and Account
        Routine newRoutine = new Routine();
        newRoutine.setName(dto.getName());
        newRoutine.setAccount(account);

        // Saves routine to database
        routineService.createRoutine(newRoutine);
        System.out.println("New routine has been created. ID: " + newRoutine.getId());

        // Returns an ok response entity
        return ResponseEntity.ok(newRoutine);
    }

    // TODO: Verify that Workout and Routine are under the user's account (?)
    // Could skip ^ if we decide that multiple users have access to same workout, but not plausible because
    // of logging for workout weights. Might be useful to have people share empty routine templates with
    // each other? Or maybe have routines posted on a user's account and other users can view routines and then
    // copy them for themselves.
    @PostMapping("/{routineId}/workouts")
    public ResponseEntity<RoutineDTO> addWorkoutsToRoutine(@RequestBody AddWorkoutsToRoutineDTO dto) {
        Routine routine = routineService.addWorkoutsToRoutine(dto.getRoutineId(), dto.getWorkoutIds());
        RoutineDTO returnDTO = new RoutineDTO(routine);
        return ResponseEntity.ok(returnDTO);
    }

    // TODO: Implement copying routines and create DTO
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