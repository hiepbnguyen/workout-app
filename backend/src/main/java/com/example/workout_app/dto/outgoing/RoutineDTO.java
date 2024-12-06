package com.example.workout_app.dto.outgoing;

import java.util.List;

import com.example.workout_app.models.Account;
import com.example.workout_app.models.defaults.Routine;
import com.example.workout_app.models.defaults.RoutineWorkout;
import com.example.workout_app.models.logs.RoutineLog;

import lombok.Data;

@Data
public class RoutineDTO {
    private AccountDTO account;
    private List<Long> workoutIds;
    private List<Long> routineLogIds;
    private String name;

    public RoutineDTO(){}

    public RoutineDTO(Routine routine) {
        this.account = new AccountDTO(routine.getAccount());
        this.workoutIds = routine.getRoutineWorkouts().stream().map((RoutineWorkout w) -> w.getWorkout().getId()).toList();
        System.out.println(this.workoutIds);
        this.routineLogIds = routine.getRoutineLogs().stream().map((RoutineLog l) -> l.getId()).toList();
        this.name = routine.getName();
    }
}