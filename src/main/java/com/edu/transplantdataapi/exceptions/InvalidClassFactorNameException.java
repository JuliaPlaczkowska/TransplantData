package com.edu.transplantdataapi.exceptions;

public class InvalidClassFactorNameException extends Exception{
    public InvalidClassFactorNameException(String name) {
        super("Invalid class factor name: " + name);
    }
}
