package com.coldgod.projectfbackend.Authentication.Services;

import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.Authentication.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public User save(User newUser)
    {
        //If user already exist in database then don't add anything into the database
        if(userRepository.findByUsername(newUser.getUsername()).isPresent()) return null;
        return userRepository.save(newUser);
    }
}
