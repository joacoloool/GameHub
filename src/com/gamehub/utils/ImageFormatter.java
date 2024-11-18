package com.gamehub.utils;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageFormatter {

    public static Icon urlToIcon(String url) {

        try {
            URL imageUrl = new URL(url);
            return new ImageIcon(imageUrl);
        } catch (MalformedURLException e) {
            System.out.println("Url para icon no valida.");
        }
        return null;
    }

    public static Icon upscaleIco(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public static Icon upscaleIco(Icon icon, int width, int height) {
        Image img = ((ImageIcon) icon).getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public static Icon getProfilePictureFromDirectory(String name) {
        try {
            // Cargar la imagen desde el directorio especificado
            BufferedImage icoImage = ImageIO.read(new File("images/profilePictures/" + name + ".png"));
            return new ImageIcon(icoImage);
        } catch (IOException e) {
            System.out.println("No se encontró la ruta del archivo: " + e.getMessage());
            return null;
        }
    }

    public static void saveIcon(Icon icon, String name) {

        if (icon != null) {
            try {
                // Extraer la imagen del Icon
                ImageIcon imageIcon = (ImageIcon) icon;
                BufferedImage image = (BufferedImage) imageIcon.getImage();

                // Guardar la imagen en un archivo PNG
                File outputFile = new File("images/profilePictures/" + name + ".png");
                ImageIO.write(image, "PNG", outputFile);
                System.out.println("Imagen guardada exitosamente en: " + outputFile.getAbsolutePath());

            } catch (IOException e) {
                System.out.println("Error al guardar la imagen: " + e.getMessage());
            }
        }
    }


}





