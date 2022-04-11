package dev.gungeon.utilities.exceptions;

public class ElementNotFoundException extends Exception {
    //for discovering something that doesn't exist
    public ElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
