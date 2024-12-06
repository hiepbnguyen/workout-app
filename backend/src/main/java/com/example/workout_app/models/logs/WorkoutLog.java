package com.example.workout_app.models.logs;

import java.time.LocalDateTime;
import java.util.List;

import com.example.workout_app.models.defaults.Workout;

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

/*
 * A new WorkoutLog entry will be created for each RoutineLog.
 * We can store the order inside this WorkoutLog and not a JoinTable
 * We can also remove the JoinTable entirely and make this a OneToMany relation (TODO)
 * So long as there is a reference to the original workout, we can always perform operations similar to ManyToMany relations
 */
@Entity
@Table
@Data
public class WorkoutLog {
    @Id
    @SequenceGenerator(name = "workout_sequence", sequenceName = "workout_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_log_id")
    private RoutineLog routineLogs;

    @OneToMany(mappedBy = "workoutLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkSetLog> setLogs;
    
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout refWorkout;
    
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int position;
}