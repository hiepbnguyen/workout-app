package com.example.workout_app.dto;

import io.micrometer.common.lang.NonNull;

public class LoginFormDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
