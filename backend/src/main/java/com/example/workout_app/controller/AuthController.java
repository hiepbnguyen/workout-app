package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.LoginFormDTO;
import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.security.SecurityUser;
import com.example.workout_app.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping(path="api/v1/auth")
public class AuthController {
    @Autowired
    public final AccountService accountService;

    @Autowired
    public final AuthenticationManager authenticationManager;

    @Autowired
    public final SecurityContextRepository securityContextRepository;


    public AuthController(AccountService accountService, AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterFormDTO registerForm, HttpServletRequest request) {
        registerForm.validatePassword();

        accountService.addNewAccount(registerForm);
        
        return ResponseEntity.ok("User registered successfully");
    }
    
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginFormDTO loginForm, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            securityContextRepository.saveContext(context, request, response);
            
            // SecurityContextHolder.getContext().setAuthentication(auth);
            // HttpSession session = request.getSession(true);
            // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch (Exception e) {
            System.out.println("Invalid credentials...");
            return new ResponseEntity<String>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            System.out.println("Authentication failed: SecurityContext is null");
        } else {
            System.out.println("Authenticated user: " + auth.getName());
        }
        // HttpSession session = request.getSession();


        return ResponseEntity.ok("User logged in successfully");
    }
    
    @GetMapping("secured")
    public String secured() {
        System.out.println("ASDIFQIOWEFJOSADIPJOP");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(String.format("The user is authenticated: %B", auth.isAuthenticated()));
        String username = ((SecurityUser) auth.getPrincipal()).getUsername();

        return String.format("Hello, %s!", username);
    }
}
