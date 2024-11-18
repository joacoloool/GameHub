package com.gamehub.exceptions;

public class StringTooShort extends RuntimeException {
    public StringTooShort(String message) {
        super(message);
    }
}
