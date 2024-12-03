package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.Routine;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.security.SecurityUser;
import com.example.workout_app.service.RoutineService;

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

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @PostMapping()
    public ResponseEntity<Routine> createRoutine(@RequestParam String name) {
        // Retrieves UserEntity from Security Context Holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        UserEntity user = securityUser.getUser();

        // Creates a new Routine with a Name and UserEntity
        Routine newRoutine = new Routine();
        newRoutine.setName(name);
        newRoutine.setUserEntity(user);

        // Saves routine to database
        routineService.createRoutine(newRoutine);
        System.out.println("New routine has been created. ID: " + newRoutine.getId());

        // Returns an ok response entity
        return ResponseEntity.ok(newRoutine);
    }
}