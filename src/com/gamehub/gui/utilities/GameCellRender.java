package com.gamehub.gui.utilities;

import com.gamehub.models.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Clase GameCellRender que personaliza el renderizado de células en una lista de juegos.
 * Extiende DefaultListCellRenderer.
 */
public class GameCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Llama al método de la clase base para obtener el componente básico
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Game game) { // Verifica si el valor es un objeto Game
            setText(game.getTitle()); // Establece el texto como el título del juego

            Icon icon = game.getIcon(); // Obtiene el icono del juego
            if (icon != null) {
                setIcon(icon); // Establece el icono
            } else {
                System.out.println("No se cargó el icono correctamente."); // Mensaje de depuración
            }

            // Cambia el color del texto a azul si el juego es favorito
            if (game.getFavorite()) {
                setForeground(Color.decode("#FDFD96"));
            } else {
                setForeground(Color.LIGHT_GRAY); // Color predeterminado para los no favoritos
            }
        }

        return this; // Devuelve el componente renderizado
    }
}