package com.example.workout_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.workout_app.dto.LoginFormDTO;
import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.Account;
import com.example.workout_app.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private final AccountService accountService;
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getMethodName() {
        return accountService.getAccounts();
    }

    // Registers user using form
    @PostMapping("register")
    public String registerNewUser(@ModelAttribute @Valid RegisterFormDTO registerForm, HttpServletRequest request) {
        // Validates password
        registerForm.validatePassword();

        // Adds new user into database
        accountService.addNewAccount(registerForm);
        return "User registered successfully";
    }
    
    // Logins user using form
    @PostMapping("login")
    public ResponseEntity<String> loginUser(@ModelAttribute @Valid LoginFormDTO loginForm, HttpServletRequest request) {
        // Attempts to log in the user
        accountService.loginAccount(loginForm);

        // Creates a new session, and stores the session in the HttpSessionSecurityContextRepository to be called in for future requests.
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
    }

    // Deletes user based on path variable
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        accountService.deleteAccount(id);
    }

    // TODO: Change user param into a DTO
    // Updates user using ???
    @PutMapping("{accountId}")
    public void updateUser(@PathVariable("accountId") Long accountId, @RequestBody Account account) {
        accountService.updateAccount(accountId, account);
    }

    // Returns user account info
    @GetMapping("{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") Account account) {
        return ResponseEntity.ok(account);
    }

    // Only accessible if user is logged in
    // Used to test if a user is logged in. Returns 403 if not.
    @GetMapping("secured")
    public String secured(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(1);
        System.out.println(auth.isAuthenticated());
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        // String username = "Hi";

        return String.format("Hello, %s!", username);
    }
}