package com.example.workout_app.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    @Autowired
    private final SecurityUserDetailsService jpaUserDetailsService;

    public SecurityConfigs(SecurityUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // auth.requestMatchers("/api/v1/user/secured").authenticated();
                // auth.requestMatchers(HttpMethod.POST, "/api/v1/user").hasRole("ANONYMOUS");
                .requestMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/user/secured").authenticated()
                .requestMatchers(HttpMethod.GET, "/error").permitAll()
                // .requestMatchers("/").hasRole("ADMIN")
                // .requestMatchers("/api/v1/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            // .oauth2ResourceServer(oauth2 -> oauth2
            //     .jwt(Customizer.withDefaults())
            // )
            .userDetailsService(jpaUserDetailsService)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
            )
            // .oauth2Login(Customizer.withDefaults())
            // .formLogin(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration ccfg = new CorsConfiguration();
                ccfg.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                ccfg.setAllowedMethods(Collections.singletonList("*"));
                ccfg.setAllowCredentials(true);
                ccfg.setAllowedHeaders(Collections.singletonList("*"));
                ccfg.setExposedHeaders(Arrays.asList("Authorization"));;
                ccfg.setMaxAge(3600L);
                return ccfg;
            }
        };
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