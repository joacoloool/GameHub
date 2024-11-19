package com.gamehub.exceptions;

/**
 * Clase DuplicateElementException que representa una excepción personalizada.
 * Esta excepción se utiliza para indicar que se ha intentado agregar un elemento
 * que ya existe, lo que resulta en un conflicto de duplicados.
 */
public class DuplicateElementException extends RuntimeException {

    /**
     * Constructor que crea una nueva instancia de DuplicateElementException.
     *
     * @param message Mensaje que describe la razón de la excepción.
     */
    public DuplicateElementException(String message) {
        super(message); // Llama al constructor de la clase base con el mensaje proporcionado
    }
}