package com.gamehub.gui.utilities;

import com.gamehub.models.User;
import com.gamehub.utils.ImageFormatter;

import javax.swing.*;
import java.awt.*;
import javax.swing.ListCellRenderer;

public class FriendsCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof User user) {
            setText(user.getName());
            setFont(new Font("Arial", Font.PLAIN, 15));

            
            Icon icon = ImageFormatter.upscaleIco(user.getProfileImage(),32,32); // Obtener la imagen de perfil
            if (icon != null) {
                setIcon(icon);

            } else {
                System.out.println("No se cargó la imagen de perfil correctamente.");
            }
        }

        return this;
    }
}