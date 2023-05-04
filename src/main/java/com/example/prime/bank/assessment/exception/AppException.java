package com.example.prime.bank.assessment.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
    private HttpStatus httpStatus;
    public AppException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
