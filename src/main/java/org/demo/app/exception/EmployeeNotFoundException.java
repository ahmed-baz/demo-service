package org.demo.app.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String key) {
        super("employee is not found with " + key);
    }

    public EmployeeNotFoundException(Long id) {
        super("employee is not found with " + id);
    }
}
