package com.example.workout_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfigs(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // auth.requestMatchers("/api/v1/user/secured").authenticated();
                // auth.requestMatchers(HttpMethod.POST, "/api/v1/user").hasRole("ANONYMOUS");
                .requestMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/login").permitAll()
                // .requestMatchers("/").hasRole("ADMIN")
                // .requestMatchers("/api/v1/**").hasRole("USER")
                .anyRequest().hasAnyRole("ADMIN", "USER")
            )
            // .oauth2ResourceServer(oauth2 -> oauth2
            //     .jwt(Customizer.withDefaults())
            // )
            .userDetailsService(jpaUserDetailsService)
            // .oauth2Login(Customizer.withDefaults())
            // .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}