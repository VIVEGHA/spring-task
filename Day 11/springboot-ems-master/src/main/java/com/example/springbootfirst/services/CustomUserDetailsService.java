package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RegisterDetailRepository registerDetailRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        3 things
        1.Loading data from yourDatabase
        2.setting up the authorities
        3.returning up proper user details
         */
        //Step 1
        RegisterDetails user = registerDetailRepository.findByUserName(username)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        //Step 2
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                .collect(Collectors.toSet());
        System.out.println("Username is " + user.getUserName()+"\n Password is " + user.getPassword()+"\n Authorities is "+authorities);
        return new User(user.getUserName(),user.getPassword() ,authorities);
    }
}
