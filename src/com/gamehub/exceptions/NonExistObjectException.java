package com.gamehub.exceptions;

public class NonExistObjectException extends RuntimeException {
    public NonExistObjectException(String message) {
        super(message);
    }
}
