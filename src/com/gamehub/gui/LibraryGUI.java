/*
 * Created by JFormDesigner on Wed Nov 13 17:52:52 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Administrator
 */
public class LibraryGUI extends JPanel {
    public LibraryGUI() {
        initComponents();
    }

    private void libraryButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void profileButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
        scrolPanelGames = new JScrollPane();
        games = new JList();
        addGame = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
        . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing
        .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
        Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
        ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
        public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName (
        ) ) )throw new RuntimeException( ) ;} } );

        //======== scrolPanelGames ========
        {
            scrolPanelGames.setViewportView(games);
        }

        //---- addGame ----
        addGame.setText("Add Game +");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrolPanelGames, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addComponent(addGame, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                    .addContainerGap(709, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrolPanelGames, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(addGame)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
    private JScrollPane scrolPanelGames;
    private JList games;
    private JButton addGame;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
