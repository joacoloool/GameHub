package com.gamehub.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * La clase {@code ImageFormatter} proporciona métodos estáticos para manipular imágenes,
 * incluyendo la conversión de URLs a iconos, escalado de iconos, carga y guardado de imágenes
 * desde y hacia archivos.
 */
public class ImageFormatter {

    /**
     * Convierte una URL de imagen en un objeto {@code Icon}.
     *
     * @param url la URL de la imagen como {@code String}
     * @return un objeto {@code Icon} representando la imagen, o {@code null} si la URL es inválida
     */
    public static Icon urlToIcon(String url) {
        try {
            URL imageUrl = new URL(url);
            return new ImageIcon(imageUrl);
        } catch (MalformedURLException e) {
            System.out.println("Url para icon no valida.");
        }
        return null;
    }

    /**
     * Escala un icono a un tamaño de 20x20 píxeles.
     *
     * @param icon el icono a escalar
     * @return un nuevo {@code Icon} escalado, o {@code null} si el icono es {@code null}
     */
    public static Icon upscaleIco(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    /**
     * Escala un icono a un tamaño especificado.
     *
     * @param icon   el icono a escalar
     * @param width  el ancho deseado del icono
     * @param height la altura deseada del icono
     * @return un nuevo {@code Icon} escalado, o el icono original si no se requiere escalado
     */
    public static Icon upscaleIco(Icon icon, int width, int height) {
        try {
            if (icon.getIconHeight() != height && icon.getIconWidth() != width) {
                try {
                    Image img = ((ImageIcon) icon).getImage();
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return icon;
    }

    /**
     * Carga un icono desde un archivo en el sistema de archivos.
     *
     * @param file el archivo de imagen
     * @return un objeto {@code Icon} representando la imagen, o {@code null} si no se pudo cargar
     */
    public static Icon getIconFromFile(File file) {
        try {
            BufferedImage icoImage = ImageIO.read(file);
            return new ImageIcon(icoImage);
        } catch (IOException e) {
            System.out.println("No se encontró la ruta del archivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda un icono como una imagen de perfil en un archivo.
     *
     * @param directory el nombre del archivo (sin extensión) donde se guardará la imagen
     * @param icon      el icono a guardar
     */
    public static void saveProfileImageToFile(String directory, Icon icon) {
        if (icon != null) {
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            File dir = new File("src/com/gamehub/images/profilePictures");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, directory + ".png");
            try {
                ImageIO.write(bufferedImage, "PNG", file);
            } catch (IOException e) {
                System.out.println("Error al guardar la imagen de perfil: " + e.getMessage());
            }
        } else {
            System.out.println("No hay imagen de perfil para guardar.");
        }
    }

    /**
     * Carga una imagen de perfil desde un archivo.
     *
     * @param filePath el nombre del archivo (sin extensión) desde donde se cargará la imagen
     * @return un objeto {@code Icon} representando la imagen, o {@code null} si no se pudo cargar
     */
    public static Icon loadProfileImageFromFile (String filePath) {
        File file = new File("src/com/gamehub/images/profilePictures", filePath + ".png");
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                return new ImageIcon(bufferedImage);
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen de perfil: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe: " + filePath);
        }
        return null;
    }
}