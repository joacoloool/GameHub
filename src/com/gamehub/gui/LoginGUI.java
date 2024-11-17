/*
 * Created by JFormDesigner on Fri Nov 15 23:23:38 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.gamehub.models.Game;
import com.gamehub.models.User;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Administrator
 */
public class LoginGUI extends JPanel {

    public LoginGUI(TreeSet<User> users) {
        initComponents();
    }


    private void okButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void closeButtonMouseClicked(MouseEvent e) {
        System.exit(0);
    }

    private void createAccountButtonMouseClicked(MouseEvent e) {


        
    }

    private void backButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        SignInL = new JLabel();
        passwordL = new JLabel();
        rememberMeCheck = new JCheckBox();
        okButton = new JButton();
        userField = new JTextField();
        passwordWarning = new JLabel();
        gamehubIcon = new JLabel();
        gamehubL = new JLabel();
        closeButton = new JButton();
        createAccountButton = new JButton();
        label1 = new JLabel();
        passwordField1 = new JPasswordField();
        register = new JPanel();
        SignInL2 = new JLabel();
        passwordL2 = new JLabel();
        okButton2 = new JButton();
        nameF = new JTextField();
        passwordWarning2 = new JLabel();
        gamehubIcon2 = new JLabel();
        gamehubL2 = new JLabel();
        closeButton2 = new JButton();
        passwordF = new JPasswordField();
        confirmPF = new JPasswordField();
        passwordL3 = new JLabel();
        backButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

        //---- SignInL ----
        SignInL.setText("SIGN IN WITH ACCOUNT NAME");
        SignInL.setBackground(Color.black);
        SignInL.setForeground(new Color(0x1a99eb));
        SignInL.setFont(new Font("Inter", Font.BOLD, 13));

        //---- passwordL ----
        passwordL.setText("PASSWORD");

        //---- rememberMeCheck ----
        rememberMeCheck.setText("Remember me");

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
        passwordWarning.setEnabled(false);

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

        //---- label1 ----
        label1.setText("Don\u00b4t have a GameHub account? ");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(127, 127, 127)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordL, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(SignInL, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userField, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                        .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                        .addComponent(rememberMeCheck))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                            .addGap(390, 390, 390)
                            .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createAccountButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                            .addGap(96, 96, 96))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
                            .addGap(191, 191, 191))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(gamehubL, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addComponent(gamehubIcon, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(SignInL)
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
                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordWarning, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(createAccountButton))
                    .addContainerGap(27, Short.MAX_VALUE))
        );

        //======== register ========
        {
            register.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,register. getBorder( )) ); register. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );

            //---- SignInL2 ----
            SignInL2.setText("ACCOUNT NAME");
            SignInL2.setBackground(Color.black);
            SignInL2.setForeground(new Color(0x1a99eb));
            SignInL2.setFont(new Font("Inter", Font.BOLD, 13));

            //---- passwordL2 ----
            passwordL2.setText("PASSWORD");

            //---- okButton2 ----
            okButton2.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Facultad\\Github\\GameHub10\\src\\com\\gamehub\\images\\headers\\signButton.png"));
            okButton2.setBorder(new EtchedBorder());
            okButton2.setHorizontalTextPosition(SwingConstants.CENTER);
            okButton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    okButtonMouseClicked(e);
                }
            });

            //---- passwordWarning2 ----
            passwordWarning2.setForeground(new Color(0xd23d41));
            passwordWarning2.setEnabled(false);

            //---- gamehubIcon2 ----
            gamehubIcon2.setText("text");
            gamehubIcon2.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/hubIco.png")));

            //---- gamehubL2 ----
            gamehubL2.setText("GAMEHUB\u00ae");
            gamehubL2.setFont(new Font("Yu Gothic", Font.PLAIN, 25));

            //---- closeButton2 ----
            closeButton2.setText("X");
            closeButton2.setBorder(new MatteBorder(1, 0, 0, 1, new Color(0x1a99eb)));
            closeButton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    closeButtonMouseClicked(e);
                }
            });

            //---- passwordL3 ----
            passwordL3.setText("CONFIRM PASSWORD");

            //---- backButton ----
            backButton.setText("<");
            backButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    backButtonMouseClicked(e);
                }
            });

            GroupLayout registerLayout = new GroupLayout(register);
            register.setLayout(registerLayout);
            registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup()
                    .addGroup(registerLayout.createSequentialGroup()
                        .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(registerLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(registerLayout.createSequentialGroup()
                                        .addComponent(gamehubIcon2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(gamehubL2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                        .addGap(390, 390, 390)
                                        .addComponent(closeButton2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registerLayout.createSequentialGroup()
                                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(passwordWarning2, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(okButton2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63))))
                            .addGroup(GroupLayout.Alignment.LEADING, registerLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SignInL2, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameF, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                    .addComponent(passwordF, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                    .addComponent(passwordL2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordL3, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(confirmPF, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup()
                    .addGroup(registerLayout.createSequentialGroup()
                        .addGroup(registerLayout.createParallelGroup()
                            .addGroup(registerLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(gamehubL2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gamehubIcon2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SignInL2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameF, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                            .addComponent(closeButton2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordL2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordF, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordL3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmPF, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(okButton2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGroup(registerLayout.createSequentialGroup()
                                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordWarning2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addContainerGap(49, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JLabel SignInL;
    private JLabel passwordL;
    private JCheckBox rememberMeCheck;
    private JButton okButton;
    private JTextField userField;
    private JLabel passwordWarning;
    private JLabel gamehubIcon;
    private JLabel gamehubL;
    private JButton closeButton;
    private JButton createAccountButton;
    private JLabel label1;
    private JPasswordField passwordField1;
    private JPanel register;
    private JLabel SignInL2;
    private JLabel passwordL2;
    private JButton okButton2;
    private JTextField nameF;
    private JLabel passwordWarning2;
    private JLabel gamehubIcon2;
    private JLabel gamehubL2;
    private JButton closeButton2;
    private JPasswordField passwordF;
    private JPasswordField confirmPF;
    private JLabel passwordL3;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
