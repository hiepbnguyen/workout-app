package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getMethodName() {
        return userService.getPlayers();
    }

    @PostMapping
    public void registerNewPlayer(@RequestBody RegisterFormDTO user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("{playerId}")
    public void updatePlayer(@PathVariable("playerId") Long playerId, @RequestBody UserEntity player) {
        userService.updateUser(playerId, player);
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, secured!";
    }
}