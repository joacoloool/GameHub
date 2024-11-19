package com.gamehub.gui.utilities;
import com.gamehub.models.Achievement;
import javax.swing.*;
import java.awt.*;

/**
 * Clase AchievementCellRender que personaliza el renderizado de células en una lista de logros.
 * Extiende DefaultListCellRenderer.
 */
public class AchievementCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Llama al metodo de la clase base para obtener el componente básico
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Verifica si el valor es un objeto Achievement
        if (value instanceof Achievement achievement) {
            setText(achievement.getName()); // Establece el texto como el nombre del logro

            Icon icon = achievement.getIcon(); // Obtiene el icono del logro
            if (icon != null) {
                setIcon(icon); // Establece el icono si no es nulo
            } else {
                System.out.println("No se cargó el icono del logro correctamente."); // Mensaje de depuración
            }

            // Cambia el color del texto basado en si el elemento está seleccionado
            if (isSelected) {
                setForeground(Color.WHITE); // Cambia el color del texto a blanco si está seleccionado
            } else {
                setForeground(Color.GRAY); // Cambia el color del texto a gris para elementos no seleccionados
            }

            // Cambiar el tamaño del icono (ajustado a 64x64)
            int iconWidth = 64;  // Ancho del icono
            int iconHeight = 64; // Alto del icono
            Image image = icon != null ? ((ImageIcon) icon).getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH) : null;

            try {
                setIcon(new ImageIcon(image)); // Establece el icono redimensionado
            } catch (NullPointerException e) {
                setIcon(null); // Si ocurre una excepción, establece el icono como nulo
            }
        }

        return this; // Devuelve el componente renderizado
    }
}