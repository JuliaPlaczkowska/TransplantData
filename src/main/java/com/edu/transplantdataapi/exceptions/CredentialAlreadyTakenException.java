package com.edu.transplantdataapi.exceptions;

public class CredentialAlreadyTakenException extends RuntimeException {
    public CredentialAlreadyTakenException(String credentialType) {
        super("Error: " + credentialType + " is already taken!");
    }
}
