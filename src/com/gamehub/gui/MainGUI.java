/*
 * Created by JFormDesigner on Thu Nov 14 13:24:57 ART 2024
 */
package com.gamehub.gui;
import javax.swing.border.*;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.gamehub.managers.Manager;
import com.gamehub.models.User;

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




    public MainGUI(Manager manager,User selectedUser) {
        initComponents();
        this.selectedUser = selectedUser;
        libraryGUI = new LibraryGUI(manager.getUsers().first());
        profileGUI = new ProfileGui(manager, manager.getUsers().getFirst());
        this.manager=manager;

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
    }

    private void profileButtonMouseClicked(MouseEvent e) {//por algun motivo los botones estan invertidos y funcionan xd
        container.add(libraryGUI);
        profileGUI.updateProfile(selectedUser,manager);
        profileButton.setForeground(Color.decode("#1a99eb"));

        if (Objects.equals(libraryButton.getForeground(), Color.decode("#1a99eb"))) {
            libraryButton.setForeground(Color.white);
        }
        active = true;
        libraryButton.setEnabled(false);

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

    private void profileButtonMouseEntered(MouseEvent e) {
    if (!active){
        profileButton.setEnabled(true);
    }


    }

    private void profileButtonMouseExited(MouseEvent e) {
    if (!active){
        profileButton.setEnabled(false);
    }
    }

    private void libraryButtonMouseEntered(MouseEvent e) {
        if (active){
            libraryButton.setEnabled(true);
        }
    }

    private void libraryButtonMouseExited(MouseEvent e) {
        if (active){
            libraryButton.setEnabled(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        libraryButton = new JButton();
        profileButton = new JButton();
        container = new JPanel();
        layeredPane1 = new JLayeredPane();
        userNameText = new JLabel();
        label1 = new JLabel();

        //======== this ========
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
            container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,container. getBorder( )) ); container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            container.setLayout(new CardLayout());
        }

        //======== layeredPane1 ========
        {

            //---- userNameText ----
            userNameText.setText("userName");
            userNameText.setForeground(new Color(0x1a99eb));
            userNameText.setFont(new Font("Inter", Font.BOLD, 12));
            layeredPane1.add(userNameText, JLayeredPane.DEFAULT_LAYER);
            userNameText.setBounds(95, -3, 86, 24);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/defaultProfilePicThumb.jpg")));
            layeredPane1.add(label1, JLayeredPane.DEFAULT_LAYER);
            label1.setBounds(70, 0, 20, 20);
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
                    .addComponent(container, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
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
    private JLayeredPane layeredPane1;
    private JLabel userNameText;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
