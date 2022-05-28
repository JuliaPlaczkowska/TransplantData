package com.edu.transplantdataapi.business.service.user;

import com.edu.transplantdataapi.entities.user.User;
import com.edu.transplantdataapi.exceptions.UserNotFoundException;
import com.edu.transplantdataapi.integration.repository.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationManager {

    private UserRepo userRepo;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if(authentication.isAuthenticated()){
            Optional<User> optionalUser = userRepo.findByUsername(authentication.getName());
            if (optionalUser.isPresent())
                user = optionalUser.get();
            else throw new UserNotFoundException(authentication.getName());
        }
        return user;
    }
}
