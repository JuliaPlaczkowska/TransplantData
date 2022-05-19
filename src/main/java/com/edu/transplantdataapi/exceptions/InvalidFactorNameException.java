package com.edu.transplantdataapi.exceptions;

public class InvalidFactorNameException extends Exception{
    public InvalidFactorNameException(String name) {
        super("Invalid factor name: " + name);
    }
}
