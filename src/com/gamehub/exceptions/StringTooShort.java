package com.gamehub.exceptions;

/**
 * Clase StringTooShort que representa una excepción personalizada.
 * Esta excepción se utiliza para indicar que una cadena de texto es demasiado corta,
 * lo cual es especialmente relevante en el contexto de la validación de contraseñas.
 */
public class StringTooShort extends RuntimeException {

    /**
     * Constructor que crea una nueva instancia de StringTooShort.
     *
     * @param message Mensaje que describe la razón de la excepción.
     */
    public StringTooShort(String message) {
        super(message); // Llama al constructor de la clase base con el mensaje proporcionado
    }
}