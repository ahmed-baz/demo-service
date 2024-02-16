package org.demo.app.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String key) {
        super("resource not found with " + key);
    }
}
