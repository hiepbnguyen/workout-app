package com.example.workout_app.dto;

import java.util.List;

import lombok.Data;

@Data
public class AddWorkoutsToRoutineDTO {
    private Long routineId;
    private List<Long> workoutIds;
}