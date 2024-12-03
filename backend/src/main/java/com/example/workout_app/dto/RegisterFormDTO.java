package com.example.workout_app.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern.Flag;

/* To register a user, include the following:
 * email
 * password
 * name
 * dob
 */

public class RegisterFormDTO extends LoginFormDTO {
    @NotBlank(message = "Email cannot be blank.")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Flag.CASE_INSENSITIVE,
            message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 6, max = 64, message = "Password must be at least 6 and at most 64 characters long." )
    private String password;
    
    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 100, message = "Name must be at most 100 characters long.")
    private String name;

    @NotNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    public void validatePassword() {
        if (password == null) {
            throw new Error("Password cannot be null.");
        } else if (password.length() < 6 || password.length() > 64) {
            throw new Error("Password cannot be less than 6 characters or more than 64 characters.");
        } else if (!password.matches(".*[A-Z].*")){
            throw new Error("Password must have a capital letter.");
        } else if (!password.matches(".*[a-z].*")) {
            throw new Error("Password must have a lowercase letter.");
        } else if (!password.matches(".*[0-9].*")) {
            throw new Error("Password must have a number.");
        } else if (!password.matches(".*[\\W_].*")) {
            throw new Error("Password must have a special character.");
        }
    }

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
