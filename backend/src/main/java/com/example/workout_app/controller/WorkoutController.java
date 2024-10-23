package com.example.workout_app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.SetEntity;
import com.example.workout_app.service.WorkoutService;

@RestController
@RequestMapping(path = "api/v1/workouts")
public class WorkoutController {
	@Autowired
    private final WorkoutService workoutService;

	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/helo")
	public List<SetEntity> getWorkouts() {
		return workoutService.getWorkouts();
	}

	@GetMapping("/testing")
	public String testingPurposes() {
		return new String("");
	}
}