package com.example.workout_app.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.workout_app.dto.WorkSetDTO;
import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.WorkSet;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.repositories.SetRepository;
import com.example.workout_app.repositories.WorkoutRepository;
import com.example.workout_app.security.SecurityUser;

@Service
public class WorkoutService {
	private final WorkoutRepository workoutRepository;
	private final SetRepository setRepository;

	public WorkoutService(WorkoutRepository workoutRepository, SetRepository setRepository) {
		this.workoutRepository = workoutRepository;
		this.setRepository = setRepository;
	}

    public List<WorkSet> getWorkouts(){
		return List.of(
			new WorkSet.Builder()
				.reps(20)
				.weight(44)
				.build(),
			new WorkSet.Builder()
				.reps(50)
				.weight(40)
				.time(LocalTime.of(0,0,0))
				.build()
		);
    }

	@Transactional
	public Workout addSetsToWorkout(Long workoutId, List<WorkSetDTO> setDTOs){
		// Finds workout from workoutid
		Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new Error("Workout not found"));
		
		// Verify account
		Account account = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
		if (workout.getAccount() != account){
			throw new Error("User does not own workout.");
		}
		
		for (WorkSetDTO setDTO : setDTOs) {
			// Creates new set from each DTO
			WorkSet newSet = new WorkSet.Builder()
				.setNumber(setDTO.getSetNumber())
				.weight(setDTO.getWeight())
				.reps(setDTO.getReps())
				.time(setDTO.getTime())
				.build();
		
			// Links Set to Workout and saves Set to database
			newSet.setWorkout(workout);
			setRepository.save(newSet);
		}

		return workout;
	}

	public Workout createWorkout(Workout workout){
		return workoutRepository.save(workout);
	}
}
