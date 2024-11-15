package com.gamehub.utils;

import javax.swing.*;
import java.awt.*;
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

}





