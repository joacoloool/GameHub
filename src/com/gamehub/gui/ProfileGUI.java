/*
 * Created by JFormDesigner on Wed Nov 13 17:52:32 ART 2024
 */

package com.gamehub.gui;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import net.miginfocom.swing.*;

/**
 * @author Administrator
 */
public class ProfileGUI extends JPanel {
    public ProfileGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        button1 = new JButton();
        label6 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label7 = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
        javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
        .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
        .awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.
        PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
        equals(e.getPropertyName()))throw new RuntimeException();}});

        //---- label3 ----
        label3.setText("text");
        label3.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));

        //---- label4 ----
        label4.setText("Nombre usuario");

        //---- label5 ----
        label5.setText("//DESCRIPTION");

        //---- button1 ----
        button1.setText("Modificar Perfil");

        //---- label6 ----
        label6.setText("Nivel perfil");

        //---- button2 ----
        button2.setText("Perfil");

        //---- button3 ----
        button3.setText("Biblioteca");

        //---- label1 ----
        label1.setText("Juegos //Actividad reciente y horas jugadas");

        //---- label2 ----
        label2.setText("Logros");

        //---- label7 ----
        label7.setText("En linea");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(42, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                    .addGap(84, 84, 84))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(65, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button2)
                                .addComponent(button3))
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addGap(103, 103, 103)
                                    .addComponent(button1))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
                    .addGap(18, 18, 18)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(253, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    
    // obtiene las dimensiones del JLabel y
    // redimenciona la imagen al tamaño del JLabel
    
    private void setScaledImage(String imagePath, JLabel label) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        
        int width = label.getWidth();
        int height = label.getHeight();
        if (width > 0 && height > 0) {
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);// TENDRIA QUE ESCALAR ACA
            label.setIcon(new ImageIcon(scaledImage));
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton button1;
    private JLabel label6;
    private JButton button2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
