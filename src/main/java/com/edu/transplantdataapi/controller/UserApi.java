package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.user.RoleToUserForm;
import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.exceptions.InvalidRoleNameException;
import com.edu.transplantdataapi.exceptions.UserNotFoundException;
import com.edu.transplantdataapi.service.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
@CrossOrigin
public class UserApi {

    private final UserManager users;

    @PostMapping("api/admin/user/addRole")
    public UserDto addRole(@RequestBody RoleToUserForm form) {
        try {
            return users.addRole(form.getUsername(), form.getRole());
        } catch (UserNotFoundException | InvalidRoleNameException e) {
            System.out.println(e.getMessage());//TODO
        }
        return null;
    }

    @PostMapping("api/admin/user/removeRole")
    public UserDto removeRole(@RequestBody RoleToUserForm form) {
        try {
            return users.removeRole(form.getUsername(), form.getRole());
        } catch (UserNotFoundException | InvalidRoleNameException e) {
            System.out.println(e.getMessage());//TODO
        }
        return null;
    }
}

