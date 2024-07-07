package com.example.workout_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.UserEntity;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    @Query("SELECT p FROM UserEntity p WHERE p.email = ?1")
    Optional<UserEntity> findByEmail(String email);

    
}