package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.Player;
import com.example.workout_app.service.PlayerService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getMethodName() {
        return playerService.getPlayers();
    }

    @PostMapping
    public void registerNewPlayer(@RequestBody Player player) {
        playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long id) {
        playerService.deletePlayer(id);
    }

    @PutMapping("{playerId}")
    public void updatePlayer(@PathVariable("playerId") Long playerId, @RequestBody Player player) {
        playerService.updatePlayer(playerId, player);
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, secured!";
    }
}