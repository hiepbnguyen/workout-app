package com.example.workout_app.models.defaults;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Table
public class RoutineWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Routine routine;

    @ManyToOne
    private Workout workout;

    private int order;

    public RoutineWorkout(Routine routine, Workout workout, int order) {
        this.routine = routine;
        this.workout = workout;
        this.order = order;
    }
}
