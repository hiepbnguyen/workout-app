package com.example.workout_app.dto;

import java.time.LocalTime;

import lombok.Data;

@Data
public class WorkSetDTO {
    private int setNumber = -1;
    private int reps = -1;
    private float weight = -1;
    private LocalTime time = null;
}
