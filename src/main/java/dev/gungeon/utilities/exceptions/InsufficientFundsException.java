package dev.gungeon.utilities.exceptions;

public class InsufficientFundsException extends Exception {
    //no money == no withdrawal
    public InsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }
}
