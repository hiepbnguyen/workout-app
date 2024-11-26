package com.example.workout_app.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.workout_app.models.WorkSet;

@Service
public class WorkoutService {

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
}
