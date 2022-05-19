package com.edu.transplantdataapi.exceptions;

public class InvalidRoleNameException extends Exception {
    public InvalidRoleNameException(String roleName) {
        super("Invalid role name: " + roleName);
    }
}
