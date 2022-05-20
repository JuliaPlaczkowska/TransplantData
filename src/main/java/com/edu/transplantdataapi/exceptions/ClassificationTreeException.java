package com.edu.transplantdataapi.exceptions;

public class ClassificationTreeException extends RuntimeException {
    public ClassificationTreeException() {
        super("Error generating classification tree");
    }
}
