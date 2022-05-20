package com.edu.transplantdataapi.exceptions;

public class InvalidRoleNameException extends RuntimeException {
    public InvalidRoleNameException(String roleName) {
        super("Invalid role name: " + roleName);
    }
}
