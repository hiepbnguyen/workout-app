package com.example.workout_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("SELECT p FROM Account p WHERE p.email = ?1")
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);
}