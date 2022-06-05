package com.edu.transplantdataapi.presentation.controllers;


import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.business.services.user.UserManager;
import lombok.AllArgsConstructor;
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
        UserDto user = users.registerUser(userDto);
        return ResponseEntity.ok(user);
    }
}
