package com.example.workout_app.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.workout_app.models.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.workout_app.repositories.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        
        return new SecurityUser(user);
    }
}