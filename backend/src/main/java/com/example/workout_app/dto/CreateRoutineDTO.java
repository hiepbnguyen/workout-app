package com.example.workout_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoutineDTO {
    @NotBlank(message = "Name is required")
    private String name;
}
