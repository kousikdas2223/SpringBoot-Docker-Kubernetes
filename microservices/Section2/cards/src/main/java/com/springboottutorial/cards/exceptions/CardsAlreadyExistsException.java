package com.springboottutorial.cards.exceptions;

public class CardsAlreadyExistsException extends RuntimeException {

    public CardsAlreadyExistsException(String message) {
        super(message);
    }
}
