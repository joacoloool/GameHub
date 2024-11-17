/*
 * Created by JFormDesigner on Fri Nov 15 23:23:38 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.managers.Manager;
import com.gamehub.models.Game;
import com.gamehub.models.User;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author Administrator
 */
public class LoginGUI extends JFrame {

    Manager manager;
    Boolean register = false;

    public LoginGUI(Manager manager) {
        initComponents();
        this.manager = manager;
    }


    private void okButtonMouseClicked(MouseEvent e) {
        User user = new User(userField.getText(), Arrays.toString(passwordField1.getPassword()));
        MainGUI mainGUI;

        if (register) {
            try {
                // Intentamos registrar al usuario
                manager.addUser(user);
                mainGUI = new MainGUI(manager, user);
                mainGUI.setVisible(true);
                this.dispose();
            } catch (DuplicateElementException x) {
                // Mostramos un mensaje si el usuario ya existe
                passwordWarning.setText(x.getMessage());
                passwordWarning.setVisible(true);
            }
        } else {
            // Verificamos credenciales
            if (manager.findUser(user.getName(), user.getPassword())) {
                mainGUI = new MainGUI(manager, manager.getUserByName(user.getName()));
                mainGUI.setVisible(true);
                this.dispose(); // Cerramos la ventana actual
            } else {
                // Mostramos un mensaje si las credenciales no son válidas
                passwordWarning.setText("Usuario o contraseña incorrectos.");
                passwordWarning.setVisible(true);
            }
        }
    }


    private void closeButtonMouseClicked(MouseEvent e) {
        System.exit(0);
    }

    private void createAccountButtonMouseClicked(MouseEvent e) {
        gamehubL.setText("Create Account");
        signInL.setText("Name");
        createAccountButton.setVisible(false);
        textAccount.setVisible(false);
        register = true;
        passwordWarning.setVisible(false);
        backButton.setVisible(true);
    }

    private void backButtonMouseClicked(MouseEvent e) {
        gamehubL.setText("GAMEHUB®");
        gamehubL.setText("Create Account");
        signInL.setText("SIGN IN WITH ACCOUNT NAME");
        createAccountButton.setVisible(true);
        textAccount.setVisible(true);
        register = false;
        passwordWarning.setVisible(false);
        backButton.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        signInL = new JLabel();
        passwordL = new JLabel();
        rememberMeCheck = new JCheckBox();
        okButton = new JButton();
        userField = new JTextField();
        passwordWarning = new JLabel();
        gamehubIcon = new JLabel();
        gamehubL = new JLabel();
        closeButton = new JButton();
        createAccountButton = new JButton();
        textAccount = new JLabel();
        passwordField1 = new JPasswordField();
        backButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- signInL ----
        signInL.setText("SIGN IN WITH ACCOUNT NAME");
        signInL.setBackground(Color.black);
        signInL.setForeground(new Color(0x1a99eb));
        signInL.setFont(new Font("Inter", Font.BOLD, 13));

        //---- passwordL ----
        passwordL.setText("PASSWORD");

        //---- rememberMeCheck ----
        rememberMeCheck.setText("Remember me");
        rememberMeCheck.setVisible(false);

        //---- okButton ----
        okButton.setText("text");
        okButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Facultad\\Github\\GameHub10\\src\\com\\gamehub\\images\\headers\\signButton.png"));
        okButton.setBorder(new EtchedBorder());
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                okButtonMouseClicked(e);
            }
        });

        //---- passwordWarning ----
        passwordWarning.setForeground(new Color(0xd23d41));
        passwordWarning.setText("Please check your password and account name and try again");
        passwordWarning.setVisible(false);

        //---- gamehubIcon ----
        gamehubIcon.setText("text");
        gamehubIcon.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/hubIco.png")));

        //---- gamehubL ----
        gamehubL.setText("GAMEHUB\u00ae");
        gamehubL.setFont(new Font("Yu Gothic", Font.PLAIN, 25));

        //---- closeButton ----
        closeButton.setText("X");
        closeButton.setBorder(new MatteBorder(1, 0, 0, 1, new Color(0x1a99eb)));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeButtonMouseClicked(e);
            }
        });

        //---- createAccountButton ----
        createAccountButton.setText("Create a free account");
        createAccountButton.setBorder(null);
        createAccountButton.setFont(new Font("Inter", Font.BOLD, 13));
        createAccountButton.setForeground(new Color(0x1a99eb));
        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createAccountButtonMouseClicked(e);
            }
        });

        //---- textAccount ----
        textAccount.setText("Don\u00b4t have a GameHub account? ");

        //---- backButton ----
        backButton.setText("<");
        backButton.setVisible(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backButtonMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(127, 127, 127)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordL, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(signInL, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userField, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                        .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                        .addComponent(rememberMeCheck))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(textAccount)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createAccountButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                            .addGap(96, 96, 96))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
                                    .addGap(191, 191, 191))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                    .addGap(390, 390, 390)
                                    .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(signInL)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passwordL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rememberMeCheck))
                        .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textAccount)
                        .addComponent(createAccountButton))
                    .addContainerGap(63, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JLabel signInL;
    private JLabel passwordL;
    private JCheckBox rememberMeCheck;
    private JButton okButton;
    private JTextField userField;
    private JLabel passwordWarning;
    private JLabel gamehubIcon;
    private JLabel gamehubL;
    private JButton closeButton;
    private JButton createAccountButton;
    private JLabel textAccount;
    private JPasswordField passwordField1;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
