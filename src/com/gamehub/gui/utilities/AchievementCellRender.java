package com.gamehub.gui.utilities;

import com.gamehub.models.Achievement;

import javax.swing.*;
import java.awt.*;

public class AchievementCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Borrar el texto para que no se muestre nada


        if (value instanceof Achievement achievement) {
            setText(achievement.getName());
            Icon icon = achievement.getIcon();
            if (icon != null) {
                setIcon(icon);
            } else {
                System.out.println("No se cargó el icono del logro correctamente.");
            }

            if (isSelected) {
                setForeground(Color.WHITE); // Cambia el color del texto a blanco
            } else {
                setForeground(Color.GRAY); // Cambia el color del texto a negro para elementos no seleccionados
            }

            // Cambiar el tamaño del icono (si lo deseas más grande)
            int iconWidth = 64;  // Ajusta el tamaño
            int iconHeight = 64; // Ajusta el tamaño
            Image image = icon != null ? ((ImageIcon) icon).getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH) : null;
            try{
                setIcon(new ImageIcon(image));
            }
            catch (NullPointerException e){
                setIcon(null);
            }



        }

        return this;
    }
}