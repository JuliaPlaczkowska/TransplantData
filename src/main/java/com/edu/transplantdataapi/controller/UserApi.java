package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.user.RoleToUserForm;
import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.exceptions.InvalidRoleNameException;
import com.edu.transplantdataapi.exceptions.UserNotFoundException;
import com.edu.transplantdataapi.service.UserManager;
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
    public ResponseEntity<?> addRole(@RequestBody RoleToUserForm form) {
            return ResponseEntity.ok(users.addRole(form.getUsername(), form.getRole()));
    }

    @PostMapping("api/admin/user/removeRole")
    public ResponseEntity<?> removeRole(@RequestBody RoleToUserForm form) {
            return ResponseEntity.ok(users.removeRole(form.getUsername(), form.getRole()));
    }
}

