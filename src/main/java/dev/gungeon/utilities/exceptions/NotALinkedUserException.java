package dev.gungeon.utilities.exceptions;

public class NotALinkedUserException extends Exception {
    public NotALinkedUserException(String errorMessage) {
        super(errorMessage);
    }
}
