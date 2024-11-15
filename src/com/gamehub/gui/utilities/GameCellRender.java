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
                System.out.println("No se cargo el icono correctamente.");
            }
        }


        return this;
    }
}
