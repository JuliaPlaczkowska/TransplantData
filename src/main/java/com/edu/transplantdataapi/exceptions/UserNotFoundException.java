package com.edu.transplantdataapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Cannot find user: " + username);
    }
}
