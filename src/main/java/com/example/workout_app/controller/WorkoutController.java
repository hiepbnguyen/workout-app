package com.example.workout_app.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.WSet;
import com.example.workout_app.service.WorkoutService;

@RestController
@RequestMapping(path = "api/v1/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/helo")
	public List<WSet> getWorkouts() {
		return workoutService.getWorkouts();
	}
}