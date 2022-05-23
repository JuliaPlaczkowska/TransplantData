package com.edu.transplantdataapi.exceptions;

public class InvalidFactorNameException extends RuntimeException {
    public InvalidFactorNameException(String name) {
        super("Invalid factor name: " + name);
    }
}
