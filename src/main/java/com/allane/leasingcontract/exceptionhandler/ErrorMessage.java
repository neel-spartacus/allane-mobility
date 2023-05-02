package com.allane.leasingcontract.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorMessage {

    private HttpStatus status;

    private String message;

    List<String> errors;

    public ErrorMessage(HttpStatus status,String message) {
        this.status = status;
        this.message = message;
    }
    public ErrorMessage(HttpStatus status,String message,List<String> errors){
       this(status,message);
        this.errors=errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
