package com.edu.transplantdataapi.exceptions;

public class InvalidSignificanceValue extends RuntimeException {
    public InvalidSignificanceValue(double value) {
        super("Invalid significance value: " + value
                + "\n Significance value should be greater than 0.0 and max 0.5");
    }
}
