package com.edu.transplantdataapi.presentation.controllers;

import com.edu.transplantdataapi.dto.user.RoleToUserDto;
import com.edu.transplantdataapi.business.services.user.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
@CrossOrigin
public class UserApi {

    private final UserManager users;

    @PostMapping("api/admin/user/addRole")
    public ResponseEntity<?> addRole(@RequestBody RoleToUserDto form) {
            return ResponseEntity.ok(users.addRole(form.getUsername(), form.getRole()));
    }

    @PostMapping("api/admin/user/removeRole")
    public ResponseEntity<?> removeRole(@RequestBody RoleToUserDto form) {
            return ResponseEntity.ok(users.removeRole(form.getUsername(), form.getRole()));
    }
}

