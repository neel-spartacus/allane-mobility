package com.allane.leasingcontract.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}
