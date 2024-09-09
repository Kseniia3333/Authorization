package com.example.Authorization.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super("Wrong old password");
    }

}
