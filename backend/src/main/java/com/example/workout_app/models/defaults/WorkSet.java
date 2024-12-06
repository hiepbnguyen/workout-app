package com.example.workout_app.models.defaults;

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
public class WorkSet {
    @Id
    @SequenceGenerator(name = "set_sequence", sequenceName = "set_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_sequence")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private int setNumber = -1;
    private int reps = -1;
    private float weight = -1;
    private LocalTime time = null;

    // Builder Pattern
    public static class Builder {
        private int setNumber;
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

        public Builder setNumber(int SetNumber){
            setNumber = SetNumber;
            return this;
        }

        public WorkSet build(){
            return new WorkSet(this);
        }
    }

    private WorkSet(Builder builder){
        setNumber = builder.setNumber;
        weight = builder.weight;
        reps = builder.reps;
        time = builder.time;
    }

    // @Override
    // public String toString() {
    //     return "Set [weight=" + weight + ", reps=" + reps + ", time=" + time + "]";
    // }
}
