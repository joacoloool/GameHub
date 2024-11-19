/*
 * Created by JFormDesigner on Thu Nov 14 13:24:57 ART 2024
 */
package com.gamehub.gui;

import javax.swing.border.*;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.gamehub.managers.Manager;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
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
    Boolean active = false; //False = Library / True = Profile


    public MainGUI(Manager manager, User selectedUser) {
        initComponents();
        this.selectedUser = selectedUser;
        libraryGUI = new LibraryGUI(selectedUser);
        profileGUI = new ProfileGui(manager, selectedUser);
        this.manager = manager;

        container.add(libraryGUI, "Library");
        container.add(profileGUI, "Profile");
        userNameText.setText(selectedUser.getName());

        // Configuración del JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar aplicación al cerrar ventana
        this.setSize(1024, 768); // Tamaño de la ventana
        this.setLocationRelativeTo(null); // Centrar ventana en pantalla
    }

    private void libraryButtonMouseClicked(MouseEvent e) {
        container.add(profileGUI);
        libraryButton.setForeground(Color.decode("#1a99eb"));

        if (Objects.equals(profileButton.getForeground(), Color.decode("#1a99eb"))) {
            profileButton.setForeground(Color.white);
        }
        active = false;
        profileButton.setEnabled(false);

        //Logica para guardar el manager el usuario
    }

    private void profileButtonMouseClicked(MouseEvent e) {//por algun motivo los botones estan invertidos y funcionan xd
        container.add(libraryGUI);
        profileGUI.updateProfile(manager);
        profileButton.setForeground(Color.decode("#1a99eb"));

        if (Objects.equals(libraryButton.getForeground(), Color.decode("#1a99eb"))) {
            libraryButton.setForeground(Color.white);
        }
        active = true;
        libraryButton.setEnabled(false);

        //logica para guardar en manager el usuario
    }

    private void profileButtonMouseEntered(MouseEvent e) {
        if (!active) {
            profileButton.setEnabled(true);
            profileButton.setForeground(Color.white);
        }
    }

    private void profileButtonMouseExited(MouseEvent e) {
        if (!active) {
            profileButton.setEnabled(false);
        }
    }

    private void libraryButtonMouseEntered(MouseEvent e) {
        if (active) {
            libraryButton.setEnabled(true);
        }
    }

    private void libraryButtonMouseExited(MouseEvent e) {
        if (active) {
            libraryButton.setEnabled(false);
        }
    }

    private void userNameButtonMouseClicked(MouseEvent e) {
        userNameOptions.show(e.getComponent(), e.getX(), e.getY());
    }

    private void changeAccountButtonMouseClicked(MouseEvent e) {
        //Guardamos
        manager.saveModifiedUser(selectedUser);

        this.dispose();
        LoginGUI login = new LoginGUI(manager);
        login.setVisible(true);


    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    private void thisWindowClosing(WindowEvent e) {
        manager.saveModifiedUser(selectedUser);
        System.out.println(selectedUser.toJson());
        JsonUtil.guardar("manager.json", manager.toJson());
        System.out.println("Cerrado launcher..");
        manager.saveAllImages();
    }

    private void thisWindowClosed(WindowEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
        libraryButton = new JButton();
        profileButton = new JButton();
        container = new JPanel();
        layeredPane1 = new JLayeredPane();
        userNameText = new JLabel();
        profileImage = new JLabel();
        userNameButton = new JButton();
        userNameOptions = new JPopupMenu();
        changeAccountButton = new JButton();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();

        //---- libraryButton ----
        libraryButton.setText(" LIBRARY ");
        libraryButton.setFont(libraryButton.getFont().deriveFont(libraryButton.getFont().getStyle() | Font.BOLD, libraryButton.getFont().getSize() + 7f));
        libraryButton.setBorder(null);
        libraryButton.setContentAreaFilled(false);
        libraryButton.setForeground(new Color(0x1a99eb));
        libraryButton.setMaximumSize(new Dimension(128, 28));
        libraryButton.setMinimumSize(new Dimension(128, 28));
        libraryButton.setPreferredSize(new Dimension(128, 28));
        libraryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        libraryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                libraryButtonMouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                libraryButtonMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                libraryButtonMouseExited(e);
            }
        });

        //---- profileButton ----
        profileButton.setText(" PROFILE ");
        profileButton.setFont(profileButton.getFont().deriveFont(profileButton.getFont().getStyle() | Font.BOLD, profileButton.getFont().getSize() + 7f));
        profileButton.setBorder(null);
        profileButton.setContentAreaFilled(false);
        profileButton.setEnabled(false);
        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileButtonMouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                profileButtonMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                profileButtonMouseExited(e);
            }
        });

        //======== container ========
        {

            container.setLayout(new CardLayout());
        }

        //======== layeredPane1 ========
        {

            //---- userNameText ----
            userNameText.setText("userName");
            userNameText.setForeground(new Color(0x1a99eb));
            userNameText.setFont(new Font("Inter", Font.BOLD, 12));
            userNameText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            layeredPane1.add(userNameText, JLayeredPane.DEFAULT_LAYER);
            userNameText.setBounds(95, -3, 86, 24);

            //---- profileImage ----
            profileImage.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/defaultProfilePicThumb.jpg")));
            profileImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            layeredPane1.add(profileImage, JLayeredPane.DEFAULT_LAYER);
            profileImage.setBounds(70, 0, 20, 20);

            //---- userNameButton ----
            userNameButton.setBorderPainted(false);
            userNameButton.setBorder(null);
            userNameButton.setContentAreaFilled(false);
            userNameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            userNameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    userNameButtonMouseClicked(e);
                }
            });
            layeredPane1.add(userNameButton, JLayeredPane.DEFAULT_LAYER);
            userNameButton.setBounds(65, -5, 95, 30);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37))
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(container, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== userNameOptions ========
        {

            //---- changeAccountButton ----
            changeAccountButton.setText(" Change Account... ");
            changeAccountButton.setBorder(new MatteBorder(0, 1, 0, 1, new Color(0x1a99eb)));
            changeAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            changeAccountButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    changeAccountButtonMouseClicked(e);
                }
            });
            userNameOptions.add(changeAccountButton);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
    private JButton libraryButton;
    private JButton profileButton;
    private JPanel container;
    private JLayeredPane layeredPane1;
    private JLabel userNameText;
    private JLabel profileImage;
    private JButton userNameButton;
    private JPopupMenu userNameOptions;
    private JButton changeAccountButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
