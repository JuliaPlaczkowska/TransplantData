package com.edu.transplantdataapi.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String username) {
        super("Cannot find user: " + username);
    }
}
