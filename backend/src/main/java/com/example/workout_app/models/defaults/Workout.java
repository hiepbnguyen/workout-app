package com.example.workout_app.models.defaults;
import java.util.List;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.logs.WorkoutLog;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class Workout {
    @Id
    @SequenceGenerator(name = "workout_sequence", sequenceName = "workout_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_sequence")
    private Long id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "workout")
    public List<RoutineWorkout> routineWorkouts;

    // @ManyToMany
    // @JoinTable(
    //     name = "workouts_in_routine",
    //     joinColumns = @JoinColumn(name = "workout_id"),
    //     inverseJoinColumns = @JoinColumn(name = "routine_id"))
    // public List<Routine> routines;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<WorkSet> sets;

    @OneToMany(mappedBy = "refWorkout")
    private List<WorkoutLog> workoutLogs;

    private String name;

    public Workout() {}
    public Workout(Account account, String name) {
        this.account = account;
        this.name = name;
    }
}