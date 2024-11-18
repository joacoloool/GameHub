/*
 * Created by JFormDesigner on Fri Nov 15 23:23:38 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.exceptions.StringTooShort;
import com.gamehub.managers.Manager;
import com.gamehub.models.Game;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;

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

    public static String passwordToString(char[] password) {
        StringBuilder bd = new StringBuilder();
        for (char c : password) {
            bd.append(c);
        }
        return bd.toString();
    }


    private void okButtonMouseClicked(MouseEvent e) {
        // Obtener la contraseña como un String
        String password = passwordToString(passwordField1.getPassword());
        User user = new User(userField.getText(), password);
        MainGUI mainGUI;

        if (register) {
            try {
                // Intentamos registrar al usuario
                manager.addUser (user);
                mainGUI = new MainGUI(manager, user);
                mainGUI.setVisible(true);
                System.out.println(user.getPassword());
                this.dispose();
            } catch (DuplicateElementException x) {
                // Mostramos un mensaje si el usuario ya existe
                passwordWarning.setText(x.getMessage());
                passwordWarning.setVisible(true);
            } catch (StringTooShort x) {
                passwordWarning.setText(x.getMessage());
                passwordWarning.setVisible(true);
            }
        } else {
            // Verificamos credenciales
            if (manager.findUser (user.getName(), user.getPassword())) {
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private void thisWindowClosed(WindowEvent e) {
        JsonUtil.guardar("manager.json", manager.toJson());
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
        createAccountButton = new JButton();
        textAccount = new JLabel();
        passwordField1 = new JPasswordField();
        backButton = new JButton();

        //======== this ========
        setResizable(false);
        setAlwaysOnTop(true);
        setMaximumSize(new Dimension(620, 420));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
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
        okButton.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/signButton.png")));
        okButton.setBorder(new EtchedBorder());
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                okButtonMouseClicked(e);
            }
        });

        //---- userField ----
        userField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

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

        //---- createAccountButton ----
        createAccountButton.setText("Create a free account");
        createAccountButton.setBorder(null);
        createAccountButton.setFont(new Font("Inter", Font.BOLD, 13));
        createAccountButton.setForeground(new Color(0x1a99eb));
        createAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        backButton.setFont(backButton.getFont().deriveFont(backButton.getFont().getSize() + 3f));
        backButton.setBorder(new MatteBorder(1, 1, 0, 0, new Color(0x1a99eb)));
        backButton.setForeground(Color.white);
        backButton.setBackground(new Color(0x1a99eb));
        backButton.setAlignmentY(4.5F);
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
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
                            .addGap(217, 217, 217))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(textAccount)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createAccountButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
                            .addGap(14, 14, 14))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(passwordL, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(signInL, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userField, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                                .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                                .addComponent(rememberMeCheck)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(149, 149, 149)
                            .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(180, 180, 180)
                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(signInL)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(userField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(rememberMeCheck)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textAccount)
                        .addComponent(createAccountButton))
                    .addGap(21, 21, 21))
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
    private JButton createAccountButton;
    private JLabel textAccount;
    private JPasswordField passwordField1;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
