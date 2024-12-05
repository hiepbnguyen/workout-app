package com.example.workout_app.models.logs;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class WorkSetLog {
    @Id
    @SequenceGenerator(name = "set_log_sequence", sequenceName = "set_log_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_log_sequence")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_log_id")
    private WorkoutLog workoutLog;

    private int setNumber = -1;
    private int reps = -1;
    private float weight = -1;
    private LocalTime time = null;
}
