/*
 * Created by JFormDesigner on Thu Nov 14 11:42:10 ART 2024
 */

package com.gamehub.gui;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Administrator
 */
public class ProfileGUI extends JPanel {
    public ProfileGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        libraryButton = new JButton();
        profileButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );

        //---- libraryButton ----
        libraryButton.setText("Library");

        //---- profileButton ----
        profileButton.setText("Profile");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 579, Short.MAX_VALUE)
                    .addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(libraryButton)
                        .addComponent(profileButton))
                    .addContainerGap(505, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JButton libraryButton;
    private JButton profileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
