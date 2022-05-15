package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.service.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
