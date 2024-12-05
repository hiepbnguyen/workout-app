package com.example.workout_app.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.workout_app.models.defaults.WorkSet;
import com.example.workout_app.models.defaults.Workout;
import com.example.workout_app.repositories.WorkoutRepository;

@Service
public class WorkoutService {
	private final WorkoutRepository workoutRepository;

	public WorkoutService(WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
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

	public Workout createWorkout(Workout workout){
		return workoutRepository.save(workout);
	}
}
