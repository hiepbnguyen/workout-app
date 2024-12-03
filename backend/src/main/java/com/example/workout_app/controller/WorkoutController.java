package com.example.workout_app.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.models.WorkSet;
import com.example.workout_app.models.Workout;
import com.example.workout_app.service.WorkoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/workouts")
public class WorkoutController {
	@Autowired
    private final WorkoutService workoutService;

	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}


	// TODO
	@PostMapping()
	public ResponseEntity<String> createWorkout(@RequestParam("name") String name, @RequestParam Optional<List<String>> id) {
		
		
		return ResponseEntity.ok("a");
	}
	

	@GetMapping("/helo")
	public List<WorkSet> getWorkouts() {
		return workoutService.getWorkouts();
	}

	@GetMapping("/testing")
	public String testingPurposes() {
		return new String("");
	}
}