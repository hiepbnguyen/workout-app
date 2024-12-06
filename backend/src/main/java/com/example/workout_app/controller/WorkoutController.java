package com.example.workout_app.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.CreateWorkoutDTO;
import com.example.workout_app.dto.WorkSetDTO;
import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.WorkSet;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.security.SecurityUser;
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
	
    @PostMapping()
    public ResponseEntity<Workout> createWorkout(@RequestBody CreateWorkoutDTO dto) {
		System.out.println("\n\n\n" + dto.getRoutineIds().toString());
		
		// Get current account
        Account account = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        
		// Create a new workout and save in repo
        Workout newWorkout = new Workout(account, dto.getName());
        workoutService.createWorkout(newWorkout);
        
		// Return 200
        return ResponseEntity.ok(newWorkout);
    }

	@PostMapping("/sets")
	public ResponseEntity<Workout> addSetsToWorkout(@RequestBody Long workoutId, @RequestBody List<WorkSetDTO> setsDTO) {
		Workout workout = workoutService.addSetsToWorkout(workoutId, setsDTO);

		return ResponseEntity.ok(workout);
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