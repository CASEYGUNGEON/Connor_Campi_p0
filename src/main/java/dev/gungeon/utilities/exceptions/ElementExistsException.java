package dev.gungeon.utilities.exceptions;

public class ElementExistsException extends Exception {
    //for telling the user to try something new for once
    public ElementExistsException(String errorMessage) {
        super(errorMessage);
    }
}
