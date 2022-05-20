package com.edu.transplantdataapi.exceptions;

public class InvalidClassFactorNameException extends RuntimeException {
    public InvalidClassFactorNameException(String name) {
        super("Invalid class factor name: " + name);
    }
}
