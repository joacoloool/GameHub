package com.gamehub.exceptions;

/**
 * Clase UyMeLlameAmiMismoException que representa una excepción personalizada.
 * Esta excepción se utiliza para manejar el caso en el que un usuario intenta
 * agregarse a sí mismo como amigo, lo cual no está permitido.
 */
public class UyMeLlameAmiMismoException extends RuntimeException {

  /**
   * Constructor que crea una nueva instancia de UyMeLlameAmiMismoException.
   *
   * @param message Mensaje que describe la razón de la excepción.
   */
  public UyMeLlameAmiMismoException(String message) {
    super(message); // Llama al constructor de la clase base con el mensaje proporcionado
  }
}