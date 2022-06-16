package com.edu.transplantdataapi.exceptions;

public class InvalidSignificanceValueException extends RuntimeException {
    public InvalidSignificanceValueException(double value) {
        super("Invalid significance value: " + value
                + "\n Significance value should be greater than 0.0 and max 0.5");
    }
}
