package com.example.workout_app.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.workout_app.models.SetOfReps;

@Service
public class WorkoutService {

    public List<SetOfReps> getWorkouts(){
		return List.of(
			new SetOfReps.Builder()
				.reps(20)
				.weight(44)
				.build(),
			new SetOfReps.Builder()
				.reps(50)
				.weight(40)
				.time(LocalTime.of(0,0,0))
				.build()
		);
    }
}
