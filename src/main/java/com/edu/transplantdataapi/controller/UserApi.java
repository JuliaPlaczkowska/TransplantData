package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.datatransferobject.UserDto;
import com.edu.transplantdataapi.entity.Role;
import com.edu.transplantdataapi.entity.User;
import com.edu.transplantdataapi.service.UserManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class UserApi {

    private final UserManager users;

    @Autowired
    public UserApi(UserManager userManager) {
        this.users = userManager;
    }


    @PostMapping("api/user")
    public UserDto addUser(@RequestBody UserDto userDto ){
        User user =  this.users.save(convertToEntity(userDto));
        return convertToDto(user);
    }

    @PostMapping("api/admin/user/addRole")
    public UserDto addRole(@RequestBody RoleToUserForm form){
        User user =  this.users.findById(form.getId()).get();
        this.users.addRole(form.getId(), form.getRole());
        return convertToDto(user);
    }


    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    private User convertToEntity(UserDto userDto){
        return new User(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRoles()
        );
    }
}

@Data
class RoleToUserForm{
    private Long id; //user id
    private Role role;
}
