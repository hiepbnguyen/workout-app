package com.example.workout_app.models;
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate dob;

    @OneToMany(mappedBy = "userEntity")
    private List<Day> days;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;



    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserEntity() {}

    public UserEntity(String email, String password, String name, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

        String encryptedPassword = encoder.encode(password);
        this.password = encryptedPassword;

        // this.days = days;
    }

    public UserEntity(String email, String password, String name, LocalDate dob, List<Day> days) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.days = days;
    }
    
    // public UserEntity(String name, String email, LocalDate dob, List<Day> days) {
    //     this.name = name;
    //     this.email = email;
    //     this.dob = dob;
    //     this.days = days;
    // }

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
    public void setDays(List<Day> days) {
        this.days = days;
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

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", name=" + name + ", dob=" + dob + ", days=" + days + "]";
    }
}
