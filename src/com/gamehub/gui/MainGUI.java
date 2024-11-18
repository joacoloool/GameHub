/*
 * Created by JFormDesigner on Thu Nov 14 13:24:57 ART 2024
 */
package com.gamehub.gui;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.gamehub.managers.Manager;
import com.gamehub.models.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Administrator
 */
public class MainGUI extends JFrame {
    Boolean darkMode = false;
    ProfileGui profileGUI;
    LibraryGUI libraryGUI;
    Manager manager;
    User selectedUser;




    public MainGUI(Manager manager,User selectedUser) {
        initComponents();
        this.selectedUser = selectedUser;
        libraryGUI = new LibraryGUI(manager.getUsers().first());
        profileGUI = new ProfileGui(manager, manager.getUsers().getFirst());
        this.manager=manager;

        container.add(libraryGUI, "Library");
        container.add(profileGUI, "Profile");


        // Configuración del JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar aplicación al cerrar ventana
        this.setSize(1024, 768); // Tamaño de la ventana
        this.setLocationRelativeTo(null); // Centrar ventana en pantalla
    }

    private void libraryButtonMouseClicked(MouseEvent e) {
        container.add(profileGUI);
    }

    private void profileButtonMouseClicked(MouseEvent e) {//por algun motivo los botones estan invertidos y funcionan xd
        container.add(libraryGUI);
        profileGUI.updateProfile(selectedUser,manager);

    }

    private void themeButtonMouseClicked(MouseEvent e) {
        if (!darkMode) {
            setLightMode();
            darkMode = true;
        } else {
            setDarkMode();
            darkMode = false;
        }
    }

    private void setDarkMode() {
        try {

            UIManager.setLookAndFeel(new FlatGitHubDarkIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setLightMode() {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        libraryButton = new JButton();
        profileButton = new JButton();
        container = new JPanel();
        themeButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- libraryButton ----
        libraryButton.setText("Library");
        libraryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                libraryButtonMouseClicked(e);
            }
        });

        //---- profileButton ----
        profileButton.setText("Profile");
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileButtonMouseClicked(e);
            }
        });

        //======== container ========
        {
            container.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
            javax.swing.border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax
            .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
            .awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
            .Color.red),container. getBorder()));container. addPropertyChangeListener(new java.beans.
            PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".
            equals(e.getPropertyName()))throw new RuntimeException();}});
            container.setLayout(new CardLayout());
        }

        //---- themeButton ----
        themeButton.setText("debug");
        themeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                themeButtonMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(libraryButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(profileButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(themeButton)
                    .addContainerGap(658, Short.MAX_VALUE))
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                        .addComponent(themeButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(container, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JButton libraryButton;
    private JButton profileButton;
    private JPanel container;
    private JButton themeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
