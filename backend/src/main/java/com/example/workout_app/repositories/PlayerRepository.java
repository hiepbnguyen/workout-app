package com.example.workout_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.workout_app.models.Player;

import java.util.List;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

    @Query("SELECT p FROM Player p WHERE p.email = ?1")
    Optional<Player> findPlayerByEmail(String email);

    
}