package com.springboottutorial.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class CustomeralreadyExistsException extends RuntimeException{

    public CustomeralreadyExistsException(String message) {
        super(message);
    }
}
