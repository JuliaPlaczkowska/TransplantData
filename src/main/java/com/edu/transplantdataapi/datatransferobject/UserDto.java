package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.Account;
import com.edu.transplantdataapi.entity.Role;

import java.util.Set;


public class UserDto {

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;


    public UserDto( String username, String email, String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
