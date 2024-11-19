package com.gamehub.utils;
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

        if (icon.getIconHeight() != height && icon.getIconWidth() != width) {


        try{
            Image img = ((ImageIcon) icon).getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        }
        return icon;
    }


    public static Icon getIconFromFile(File file) {
        try {
            BufferedImage icoImage = ImageIO.read(file);
            return new ImageIcon(icoImage);
        } catch (IOException e) {
            System.out.println("No se encontró la ruta del archivo: " + e.getMessage());
            return null;
        }
    }

    public static void saveProfileImageToFile(String directory, Icon icon) {
        if (icon != null) {
            // Convertir el Icon en un BufferedImage
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            // Crear el directorio si no existe
            File dir = new File("src/com/gamehub/images/profilePictures");
            if (!dir.exists()) {
                dir.mkdirs(); // Crea el directorio si no existe
            }

            // Crear el archivo de destino con la ruta que incluye el directorio
            File file = new File(dir, directory+".png");  // Usando la variable 'directory' para el nombre del archivo
            try {
                // Guardar la imagen como PNG
                ImageIO.write(bufferedImage, "PNG", file);
                System.out.println("Imagen de perfil guardada en: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error al guardar la imagen de perfil: " + e.getMessage());
            }
        } else {
            System.out.println("No hay imagen de perfil para guardar.");
        }
    }

    public static Icon loadProfileImageFromFile(String filePath) {
        File file = new File("src/com/gamehub/images/profilePictures",filePath+".png");
        if (file.exists()) {
            try {
                // Leer la imagen desde el archivo
                BufferedImage bufferedImage = ImageIO.read(file);
                return new ImageIcon(bufferedImage);  // Convertir el BufferedImage a un Icon
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen de perfil: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe: " + filePath);
        }
        return null;  // Retornar null si no se pudo cargar la imagen
    }

}
