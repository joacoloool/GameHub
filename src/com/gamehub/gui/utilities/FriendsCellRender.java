package com.gamehub.gui.utilities;
import com.gamehub.models.User;
import com.gamehub.utils.ImageFormatter;
import javax.swing.*;
import java.awt.*;

/**
 * Clase FriendsCellRender que personaliza el renderizado de células en una lista de amigos.
 * Extiende DefaultListCellRenderer.
 */
public class FriendsCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Llama al método de la clase base para obtener el componente básico
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof User user) { // Verifica si el valor es un objeto User
            setText(user.getName()); // Establece el texto como el nombre del usuario
            setFont(new Font("Arial", Font.PLAIN, 15)); // Establece la fuente del texto

            // Obtiene la imagen de perfil y la ajusta a un tamaño específico
            Icon icon = ImageFormatter.upscaleIco(user.getProfileImage(), 32, 32);
            if (icon != null) {
                setIcon(icon); // Establece el icono de la imagen de perfil
            } else {
                System.out.println("No se cargó la imagen de perfil correctamente."); // Mensaje de depuración
            }
        }

        return this; // Devuelve el componente renderizado
    }
}