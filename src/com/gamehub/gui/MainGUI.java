/*
 * Created by JFormDesigner on Thu Nov 14 13:24:57 ART 2024
 */

package com.gamehub.gui;

import com.gamehub.managers.Manager;
import com.gamehub.models.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Administrator
 */
public class MainGUI extends JPanel {
    ProfileGui profileGUI;
    LibraryGUI libraryGUI;


    public MainGUI(Manager manager, User user) {
        initComponents();
        libraryGUI = new LibraryGUI(manager.getUsers().first());
        profileGUI = new ProfileGui();

        container.add(libraryGUI, "Library");
        container.add(profileGUI,"Profile");

    }


    private void libraryButtonMouseClicked(MouseEvent e) {
        container.add(profileGUI);
    }

    private void profileButtonMouseClicked(MouseEvent e) {
        container.add(libraryGUI);
    }

    private void initComponents() {
        libraryButton = new JButton();
        profileButton = new JButton();
        container = new JPanel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );

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
            container.setLayout(new CardLayout());
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(libraryButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(profileButton)
                            .addGap(0, 738, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(profileButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(container, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

    }
    private JButton libraryButton;
    private JButton profileButton;
    private JPanel container;
}
