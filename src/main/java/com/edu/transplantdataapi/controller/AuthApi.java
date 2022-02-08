package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.datatransferobject.UserDto;
import com.edu.transplantdataapi.entity.Role;
import com.edu.transplantdataapi.entity.User;
import com.edu.transplantdataapi.enums.ERole;
import com.edu.transplantdataapi.security.SignupRequest;
import com.edu.transplantdataapi.service.RoleManager;
import com.edu.transplantdataapi.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthApi {

    private UserManager users;
    private RoleManager roles;


    @Autowired
    public AuthApi(UserManager users, RoleManager roles) {

        this.users = users;
        this.roles = roles;
    }

    @PostMapping("/signin")
    public void signIn() {

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        
            if (users.existsByUsername(userDto.getUsername())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error: Username is already taken!");
            }


            if (users.existsByEmail(userDto.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error: Email is already in use!");
            }

        User user = convertToEntity(userDto);

        Set<Role> rolesSet = new HashSet<>();

        if (userDto.getRoles() == null) {
            Role userRole = roles.findByName(ERole.ROLE_USER);
            rolesSet.add(userRole);
        }

        user.setRoles(rolesSet);

        this.users.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }


    private User convertToEntity(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRoles()
        );
    }
}
