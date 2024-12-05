package com.example.workout_app.models.defaults;
import java.util.List;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.logs.RoutineLog;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Routine {
    @Id
    @SequenceGenerator(name = "routine_seq", sequenceName = "routine_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routine_seq")
    private Long id;

    // Account associated with Routine.
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // List of workouts per routine. Maintains order.
    @OneToMany(mappedBy = "routine")
    private List<RoutineWorkout> routineWorkouts;

    // @ManyToMany(mappedBy = "routines")
    // private List<Workout> workouts;

    // Logs associated with routine.
    @OneToMany(mappedBy = "refRoutine")
    private List<RoutineLog> routineLogs;
    
    private String name;
}