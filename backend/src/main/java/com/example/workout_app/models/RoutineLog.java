package com.example.workout_app.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
@SequenceGenerator(name = "routine_log_seq", sequenceName = "routine_log_seq", allocationSize = 1)
public class RoutineLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(mappedBy = "routineLogs")
    private List<WorkoutLog> workoutLogs;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine refRoutine;

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogs;
    }
    public void setWorkoutLogs(List<WorkoutLog> workoutLogs) {
        this.workoutLogs = workoutLogs;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Routine getRefRoutine() {
        return refRoutine;
    }
    public void setRefRoutine(Routine refRoutine) {
        this.refRoutine = refRoutine;
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}