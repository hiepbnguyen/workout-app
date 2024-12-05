package com.example.workout_app.models.logs;

import java.time.LocalDateTime;
import java.util.List;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.Routine;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@SequenceGenerator(name = "routine_log_seq", sequenceName = "routine_log_seq", allocationSize = 1)
public class RoutineLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "routineLogs")
    private List<WorkoutLog> workoutLogs;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine refRoutine;

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}