package com.onlineshopping.userservice.security.services;

import com.onlineshopping.userservice.models.User;
import com.onlineshopping.userservice.repository.UserRepo;
import com.onlineshopping.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user=userRepo.getUserByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new CustomUserDetails(user.get());
    }
}
