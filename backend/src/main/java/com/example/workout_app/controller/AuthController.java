package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.LoginFormDTO;
import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.security.SecurityUser;
import com.example.workout_app.service.AccountService;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping(path="api/v1/auth")
public class AuthController {
    @Autowired
    public final AccountService accountService;
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@ModelAttribute @Valid RegisterFormDTO registerForm, HttpServletRequest request) {
        registerForm.validatePassword();

        accountService.addNewAccount(registerForm);
        
        return ResponseEntity.ok("User registered successfully");
    }
    
    @PostMapping("login")
    public ResponseEntity<String> login(@ModelAttribute @Valid LoginFormDTO loginForm, HttpServletRequest request) {
        accountService.loginAccount(loginForm);
        
        HttpSession session = request.getSession();

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return ResponseEntity.ok("User logged in successfully");
    }
    
    @GetMapping("secured")
    public String getMethodName(@RequestParam String param) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(String.format("The user is authenticated: %B", auth.isAuthenticated()));
        String username = ((SecurityUser) auth.getPrincipal()).getUsername();

        return String.format("Hello, %s!", username);
    }
}
