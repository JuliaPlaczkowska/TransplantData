package com.edu.transplantdataapi.dto.user;

import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.entity.user.User;
import lombok.*;

import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
}
