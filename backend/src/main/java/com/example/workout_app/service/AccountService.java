package com.example.workout_app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.workout_app.dto.LoginFormDTO;
import com.example.workout_app.dto.RegisterFormDTO;
import com.example.workout_app.models.Account;
import com.example.workout_app.repositories.RoleRepository;
import com.example.workout_app.repositories.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.encoder = passwordEncoder;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    // Adds a new account
    public void addNewAccount(RegisterFormDTO account) {
        // Throws error if email is unavailable
        if (accountRepository.existsByEmail(account.getEmail())){
            throw new IllegalStateException("email taken");
        }
        
        Account newAccount = new Account(account.getEmail(), encoder.encode(account.getPassword()), account.getName(), account.getDob(), Collections.singletonList(roleRepository.findByName("ROLE_USER").orElseThrow()));

        accountRepository.save(newAccount);

        System.out.println(newAccount);
    }

    // Logins an account
    public void loginAccount(LoginFormDTO loginForm) {
        // Checks if credentials are valid. If so, continue. If not, throw exception
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        // String session = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId();
        // System.out.println("SESSION IS: " + session);
    }

    // Deletes an account
    public void deleteAccount(long accountId) {
        boolean exists = accountRepository.existsById(accountId);
        if (!exists){
            throw new IllegalStateException("Account with id " + accountId + " does not exist.");
        }
        accountRepository.deleteById(accountId);
    }

    // Allows changes to DOB, Name, and Email
    @Transactional
    public void updateAccount(Long accountId, Account accountUpdate) {
        System.out.println(accountUpdate);

        Account currentAccount = accountRepository.findById(accountId).orElseThrow(
            () -> new IllegalStateException("Account with id " + accountId + "does not exist.")
        );

        if (isEmailAvailable(accountUpdate.getEmail())){
            currentAccount.setEmail(accountUpdate.getEmail());
        }
        
        if (verifyName(accountUpdate.getName())){
            currentAccount.setName(accountUpdate.getName());
        }

        if (accountUpdate.getDob() != null){
            currentAccount.setDob(accountUpdate.getDob());
        }
    }

    // Verifies name is valid
    public boolean verifyName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }

    // Determines whether an email is available
    public boolean isEmailAvailable(String email) {
        if (email == null || email.length() == 0) {
            return false;
        }
        if (accountRepository.findByEmail(email).isPresent()){
            throw new IllegalStateException("email exists.");
        }
        return true;
    }

    // Determines whether an email is available given an accountId
    public boolean isEmailAvailable(String email, Long accountId) {
        if (email == null || email.length() == 0) {
            return false;
        }

        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("email exists."));
        
        if (account.getId() == accountId){
            return true;
        }
        return false;
    }
}