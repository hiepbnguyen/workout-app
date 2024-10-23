package com.example.workout_app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.workout_app.models.UserEntity;

import java.util.List;

public class SecurityUser implements UserDetails {

    private UserEntity user;

    public SecurityUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> u = user
            .getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        u.forEach( x -> System.out.println(x));
        return user
            .getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();
    }




    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return UserDetails.super.isEnabled();
    }
    
}
