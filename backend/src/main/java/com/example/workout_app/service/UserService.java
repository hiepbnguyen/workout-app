package com.example.workout_app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.repositories.RoleRepository;
import com.example.workout_app.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = passwordEncoder;
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    // Adds a new user
    public void addNewUser(RegisterFormDTO user) {
        // Throws error if email is unavailable
        if (userRepository.existsByEmail(user.getEmail())){
            throw new IllegalStateException("email taken");
        }
        
        UserEntity newUser = new UserEntity(user.getEmail(), encoder.encode(user.getPassword()), user.getName(), user.getDob(), Collections.singletonList(roleRepository.findByName("ROLE_USER").orElseThrow()));

        userRepository.save(newUser);

        System.out.println(newUser);
    }

    // Deletes a user
    public void deleteUser(long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("User with id " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    // Allows changes to DOB, Name, and Email
    @Transactional
    public void updateUser(Long userId, UserEntity userUpdate) {
        System.out.println(userUpdate);

        UserEntity currentUser = userRepository.findById(userId).orElseThrow(
            () -> new IllegalStateException("User with id " + userId + "does not exist.")
        );

        if (isEmailAvailable(userUpdate.getEmail())){
            currentUser.setEmail(userUpdate.getEmail());
        }
        
        if (verifyName(userUpdate.getName())){
            currentUser.setName(userUpdate.getName());
        }

        if (userUpdate.getDob() != null){
            currentUser.setDob(userUpdate.getDob());
        }
    }

    // Verifies name is valid
    public boolean verifyName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }

    // Determines whether an email is available
    public boolean isEmailAvailable(String email) {
        if (email == null || email.length() == 0) {
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()){
            throw new IllegalStateException("email exists.");
        }
        return true;
    }

    // Determines whether an email is available given a userId
    public boolean isEmailAvailable(String email, Long userId) {
        if (email == null || email.length() == 0) {
            return false;
        }

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("email exists."));
        
        if (user.getId() == userId){
            return true;
        }
        return false;
    }
}