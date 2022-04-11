package dev.gungeon.utilities.exceptions;

public class NotALinkedUserException extends Exception {
    //it's basically elementnotfound but it has a different name. idk, i wrote it so it's here.
    public NotALinkedUserException(String errorMessage) {
        super(errorMessage);
    }
}
