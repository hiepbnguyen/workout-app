package com.example.workout_app.models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Routine {
    @Id
    @SequenceGenerator(name = "routine_seq", sequenceName = "routine_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routine_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany(mappedBy = "routines")
    private List<Workout> workouts;
    private String name;

    @OneToMany(mappedBy = "refRoutine")
    private List<RoutineLog> routineLogs;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public List<Workout> getWorkouts() {
        return workouts;
    }
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}