/*
 * Created by JFormDesigner on Thu Nov 14 12:24:02 ART 2024
 */

package com.gamehub.gui;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author joaal
 */
public class AppGui {

    ProfileGUI profileGUI;
    LibraryGUI libraryGUI;
    JFrame frame;

    public AppGui() {
        // Inicializar los componentes y las instancias de las clases
        initComponents();
        profileGUI = new ProfileGUI();
        libraryGUI = new LibraryGUI();
        frame = new JFrame("GameManager");
        ImageIcon imageIcon = new ImageIcon("GameLauncher.png");
        frame.setIconImage(imageIcon.getImage());

        // Configuración del JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);  // Centrar la ventana
        frame.add(jose);  // Agregar este panel (AppGui) al frame
        frame.setVisible(true);
        frame.setContentPane(jose);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getJose() {
        return jose;
    }

    public void setJose(JPanel jose) {
        this.jose = jose;
    }

    private void profileButtonMouseClicked(MouseEvent e) {
        viewsPanel.removeAll();
        viewsPanel.add(profileGUI);
        viewsPanel.
        viewsPanel.revalidate();
        viewsPanel.repaint();
    }

    private void libraryButtonMouseClicked(MouseEvent e) {
        viewsPanel.removeAll();
        viewsPanel.add(libraryGUI);
        viewsPanel.revalidate();
        viewsPanel.repaint();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
        jose = new JPanel();
        libraryButton = new JButton();
        profileButton = new JButton();

        //======== jose ========
        {
            jose.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,jose. getBorder( )) ); jose. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );

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

            GroupLayout joseLayout = new GroupLayout(jose);
            jose.setLayout(joseLayout);
            joseLayout.setHorizontalGroup(
                joseLayout.createParallelGroup()
                    .addGroup(joseLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(libraryButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 577, Short.MAX_VALUE)
                        .addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
            );
            joseLayout.setVerticalGroup(
                joseLayout.createParallelGroup()
                    .addGroup(joseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(joseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(profileButton)
                            .addComponent(libraryButton))
                        .addContainerGap(505, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

   // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
   // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
   private JPanel jose;
   private JButton libraryButton;
   private JButton profileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}




