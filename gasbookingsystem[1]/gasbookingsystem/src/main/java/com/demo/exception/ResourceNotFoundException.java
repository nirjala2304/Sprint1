package com.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message); // Call the constructor of the parent RuntimeException class with the provided message.
    }
}