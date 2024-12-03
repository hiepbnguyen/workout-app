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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Workout {
    @Id
    @SequenceGenerator(name = "workout_sequence", sequenceName = "workout_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_sequence")
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "workouts_in_routine",
        joinColumns = @JoinColumn(name = "workout_id"),
        inverseJoinColumns = @JoinColumn(name = "routine_id"))
    public List<Routine> routines;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<WorkSet> sets;

    @OneToMany(mappedBy = "refWorkout")
    private List<WorkoutLog> workoutLogs;

    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Routine> getRoutines() {
        return routines;
    }
    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
    public List<WorkSet> getSets() {
        return sets;
    }
    public void setSets(List<WorkSet> sets) {
        this.sets = sets;
    }
}