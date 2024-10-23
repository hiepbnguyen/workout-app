package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.LoginFormDTO;
import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getMethodName() {
        return userService.getUsers();
    }

    @PostMapping("register")
    public String registerNewUser(@RequestBody RegisterFormDTO user) {
        userService.addNewUser(user);
        return "User registered successfully";
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginFormDTO user) {
        userService.loginUser(user);
        return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserEntity user) {
        userService.updateUser(userId, user);
    }

    @GetMapping("{userId}")
    public String getUser(@PathVariable("userId") UserEntity user) {
        return user.toString();
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, secured!";
    }
}