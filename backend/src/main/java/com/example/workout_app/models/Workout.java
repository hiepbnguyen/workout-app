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
    
    private String name;

    @ManyToMany
    @JoinTable(
        name = "workouts_in_day",
        joinColumns = @JoinColumn(name = "workout_id"),
        inverseJoinColumns = @JoinColumn(name = "day_id"))
    public List<Day> days;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<SetEntity> sets;


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Day> getDays() {
        return days;
    }
    public void setDay(List<Day> day) {
        this.days = day;
    }
    public List<SetEntity> getSets() {
        return sets;
    }
    public void setSets(List<SetEntity> sets) {
        this.sets = sets;
    }
}