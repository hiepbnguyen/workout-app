package com.example.workout_app.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class WorkoutLog {
    @Id
    @SequenceGenerator(name = "workout_sequence", sequenceName = "workout_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_sequence")
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "workouts_in_routine_log",
        joinColumns = @JoinColumn(name = "workout_log_id"),
        inverseJoinColumns = @JoinColumn(name = "routine_log_id"))
    private List<RoutineLog> routineLogs;

    @OneToMany(mappedBy = "workoutLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkSetLog> setLogs;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout refWorkout;
}