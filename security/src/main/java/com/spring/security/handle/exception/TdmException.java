package com.spring.security.handle.exception;

import org.springframework.http.HttpStatus;

public class TdmException extends Exception{

    private HttpStatus status;
    public TdmException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
