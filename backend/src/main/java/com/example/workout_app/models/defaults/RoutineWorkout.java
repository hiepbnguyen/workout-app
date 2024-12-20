package com.example.workout_app.models.defaults;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Table
@Data
public class RoutineWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Routine routine;

    @ManyToOne
    private Workout workout;

    private int position;

    public RoutineWorkout() {}
    public RoutineWorkout(Routine routine, Workout workout, int position) {
        this.routine = routine;
        this.workout = workout;
        this.position = position;
    }
}