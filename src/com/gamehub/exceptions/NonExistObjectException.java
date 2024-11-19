package com.gamehub.exceptions;

/**
 * Clase NonExistObjectException que representa una excepción personalizada.
 * Esta excepción se utiliza para indicar que un objeto no existe en el contexto
 * en el que se está intentando acceder a él.
 */
public class NonExistObjectException extends RuntimeException {

    /**
     * Constructor que crea una nueva instancia de NonExistObjectException.
     *
     * @param message Mensaje que describe la razón de la excepción.
     */
    public NonExistObjectException(String message) {
        super(message); // Llama al constructor de la clase base con el mensaje proporcionado
    }
}