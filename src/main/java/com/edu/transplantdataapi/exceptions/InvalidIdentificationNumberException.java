package com.edu.transplantdataapi.exceptions;

public class InvalidIdentificationNumberException extends RuntimeException {
    public InvalidIdentificationNumberException(String message) {
        super(message);
    }
}
