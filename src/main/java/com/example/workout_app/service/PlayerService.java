package com.example.workout_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.workout_app.models.Player;
import com.example.workout_app.repositories.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public void addNewPlayer(Player player) {
        Optional<Player> playerByEmail = playerRepository.findPlayerByEmail(player.getEmail());
        if (playerByEmail.isPresent()){
            // Unavailable email
            throw new IllegalStateException("email taken");
        }
        // Available email
        playerRepository.save(player);

        System.out.println(player);
    }

    public void deletePlayer(long playerId) {
        boolean exists = playerRepository.existsById(playerId);
        if (!exists){
            throw new IllegalStateException("player with id " + playerId + " does not exist.");
        }
        playerRepository.deleteById(playerId);
    }

    // ALlows changes to DOB, Name, and Email
    @Transactional
    public void updatePlayer(Long playerId, Player playerUpdate) {
        System.out.println(playerUpdate);

        Player currentPlayer = playerRepository.findById(playerId).orElseThrow(
            () -> new IllegalStateException("Player with id " + playerId + "does not exist.")
        );

        if (verifyEmail(playerUpdate.getEmail())){
            currentPlayer.setEmail(playerUpdate.getEmail());
        }
        
        if (verifyName(playerUpdate.getName())){
            currentPlayer.setName(playerUpdate.getName());
        }

        if (playerUpdate.getDob() != null){
            currentPlayer.setDob(playerUpdate.getDob());
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
        if (playerRepository.findPlayerByEmail(email).isPresent()){
            throw new IllegalStateException("email exists.");
        }
        return true;
    }

    public boolean verifyEmail(String email, Long playerId) {
        if (email == null || email.length() == 0) {
            return false;
        }
        Optional<Player> p = playerRepository.findPlayerByEmail(email);
        if (p.isPresent()){
            if (p.get().getId() == playerId){
                return true;
            }
            throw new IllegalStateException("email exists.");
        }
        return true;
    }
}