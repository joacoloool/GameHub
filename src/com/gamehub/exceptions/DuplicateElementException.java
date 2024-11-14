package com.gamehub.exceptions;
/** *
 * Tira una excepcion personalizada si hay un elemento duplicado.
 * */
public class DuplicateElementException extends RuntimeException {
    public DuplicateElementException(String message) {
        super(message);
    }
}
