package com.example.workout_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.UserEntity;
import com.example.workout_app.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserEntity> getPlayers() {
        return userRepository.findAll();
    }

    public void addNewUser(RegisterFormDTO user) {
        Optional<UserEntity> playerByEmail = userRepository.findByEmail(user.getEmail());

        // Throws error if email is unavailable
        if (playerByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        
        UserEntity newUser = new UserEntity(user.getEmail(), user.getPassword(), user.getName(), user.getDob());

        userRepository.save(newUser);

        System.out.println(newUser);
    }

    public void deleteUser(long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("player with id " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    // ALlows changes to DOB, Name, and Email
    @Transactional
    public void updateUser(Long userId, UserEntity userUpdate) {
        System.out.println(userUpdate);

        UserEntity currentPlayer = userRepository.findById(userId).orElseThrow(
            () -> new IllegalStateException("Player with id " + userId + "does not exist.")
        );

        if (verifyEmail(userUpdate.getEmail())){
            currentPlayer.setEmail(userUpdate.getEmail());
        }
        
        if (verifyName(userUpdate.getName())){
            currentPlayer.setName(userUpdate.getName());
        }

        if (userUpdate.getDob() != null){
            currentPlayer.setDob(userUpdate.getDob());
        }
    }

    public boolean verifyName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean verifyEmail(String email) {
        if (email == null || email.length() == 0) {
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()){
            throw new IllegalStateException("email exists.");
        }
        return true;
    }

    public boolean verifyEmail(String email, Long userId) {
        if (email == null || email.length() == 0) {
            return false;
        }
        Optional<UserEntity> p = userRepository.findByEmail(email);
        if (p.isPresent()){
            if (p.get().getId() == userId){
                return true;
            }
            throw new IllegalStateException("email exists.");
        }
        return true;
    }
}