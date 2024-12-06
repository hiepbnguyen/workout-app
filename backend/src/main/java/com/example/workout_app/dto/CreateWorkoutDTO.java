package com.example.workout_app.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateWorkoutDTO {
    private String name;
    private List<Long> routineIds;
}