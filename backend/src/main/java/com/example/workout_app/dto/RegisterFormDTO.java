package com.example.workout_app.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.micrometer.common.lang.NonNull;

/* To register a user, include the following:
 * email
 * password
 * name
 * dob
 */

public class RegisterFormDTO extends LoginFormDTO {
    @NonNull
    private String name;

    @NonNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
