package com.springboottutorial.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String entityType, String fieldName, String fieldValue) {
        super(String.format("%s with %s %s is not found", entityType, fieldName, fieldValue));
    }
}
