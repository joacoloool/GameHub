package com.gamehub.gui.utilities;

import com.gamehub.models.Game;

import javax.swing.*;
import java.awt.*;

public class GameCellRender extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Game game) {
            setText(game.getTitle()); // Mostrar el nombre del juego
            Icon icon = game.getIcon();
            if (icon != null) {
                setIcon(icon);
            } else {
                System.out.println("No se cargó el icono correctamente.");
            }

            // Cambia el color a azul si el juego es favorito
            if (game.getFavorite()) {
                setForeground(Color.decode("#FDFD96"));
            } else {
                setForeground(Color.LIGHT_GRAY); // Color predeterminado para los no favoritos
            }
        }

        return this;
    }
}
