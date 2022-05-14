package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.dto.UserDto;
import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.entity.user.User;
import com.edu.transplantdataapi.enums.ERole;
import com.edu.transplantdataapi.service.RoleManager;
import com.edu.transplantdataapi.service.UserManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthApi {

    private UserManager users;

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
        users.save(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }
}
