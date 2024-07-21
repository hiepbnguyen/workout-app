package com.example.workout_app.models;

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

@Entity
@Table
public class SetEntity {
    @Id
    @SequenceGenerator(name = "set_sequence", sequenceName = "set_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_sequence")
    private long id;
    
    private float weight = -1;

    private int reps = -1;

    private LocalTime time = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;




    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // Builder Pattern
    public static class Builder {
        private float weight = -1;
        private int reps = -1;
        private LocalTime time = null;

        public Builder(){}

        public Builder weight(float Weight){
            weight = Weight;
            return this;
        }
    
        public Builder reps(int Reps){
            reps = Reps;
            return this;
        }
    
        public Builder time(LocalTime Time){
            time = Time;
            return this;
        }

        public SetEntity build(){
            return new SetEntity(this);
        }
    }

    private SetEntity(Builder builder){
        weight = builder.weight;
        reps = builder.reps;
        time = builder.time;
    }

    @Override
    public String toString() {
        return "Set [weight=" + weight + ", reps=" + reps + ", time=" + time + "]";
    }
}
