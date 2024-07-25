package com.example.workout_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests( auth -> {
                // auth.requestMatchers("/api/v1/user/secured").authenticated();
                // auth.requestMatchers(HttpMethod.POST, "/api/v1/user").hasRole("ANONYMOUS");
                auth.anyRequest().permitAll();
            })
            // .oauth2Login(Customizer.withDefaults())
            // .formLogin(Customizer.withDefaults())
            .csrf((csrf) -> csrf.disable())
            .build();
    }
}