package com.usbank.user.accounts.loanmanager.exception;

public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
