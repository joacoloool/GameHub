/*
 * Created by JFormDesigner on Fri Nov 15 03:50:34 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;


/**
 * @author joaal
 */
public class ProfileGui extends JPanel {
    public ProfileGui() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
        profile = new JScrollPane();
        group = new JPanel();
        profileImageLabel = new JLabel();
        usernameNameLabel = new JLabel();
        descriptionLabel = new JLabel();
        button2 = new JButton();
        logroTextLabel = new JLabel();
        logroList = new JList();
        LastPlayedPanel = new JPanel();
        lastGameNameLabel = new JLabel();
        lastGameImage2 = new JLabel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        scrollPane3 = new JScrollPane();
        list2 = new JList();
        logroTextLabel2 = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
        , 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
         getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //======== profile ========
        {

            //======== group ========
            {

                //---- profileImageLabel ----
                profileImageLabel.setText("text");
                profileImageLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));
                profileImageLabel.setBorder(new EtchedBorder());

                //---- usernameNameLabel ----
                usernameNameLabel.setText("Username");

                //---- descriptionLabel ----
                descriptionLabel.setText("//DESCRIPTION");
                descriptionLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
                descriptionLabel.setBorder(new EtchedBorder());

                //---- button2 ----
                button2.setText("Modificar Perfil");

                //---- logroTextLabel ----
                logroTextLabel.setText("Logros");

                //---- logroList ----
                logroList.setBorder(new EtchedBorder());

                //======== LastPlayedPanel ========
                {
                    LastPlayedPanel.setBorder(new TitledBorder("Last Played"));

                    //---- lastGameNameLabel ----
                    lastGameNameLabel.setText("Dragon Ball Z");

                    //---- lastGameImage2 ----
                    lastGameImage2.setText("nices");
                    lastGameImage2.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));
                    lastGameImage2.setBorder(new EtchedBorder());

                    GroupLayout LastPlayedPanelLayout = new GroupLayout(LastPlayedPanel);
                    LastPlayedPanel.setLayout(LastPlayedPanelLayout);
                    LastPlayedPanelLayout.setHorizontalGroup(
                        LastPlayedPanelLayout.createParallelGroup()
                            .addGroup(LastPlayedPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lastGameImage2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lastGameNameLabel, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(70, Short.MAX_VALUE))
                    );
                    LastPlayedPanelLayout.setVerticalGroup(
                        LastPlayedPanelLayout.createParallelGroup()
                            .addGroup(LastPlayedPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(LastPlayedPanelLayout.createParallelGroup()
                                    .addComponent(lastGameImage2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastGameNameLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //---- label1 ----
                label1.setText("Feed");
                label1.setFont(new Font("JetBrains Mono", label1.getFont().getStyle() & ~Font.ITALIC, 22));

                //======== scrollPane1 ========
                {

                    //---- list1 ----
                    list1.setBorder(new EtchedBorder());
                    scrollPane1.setViewportView(list1);
                }

                //======== scrollPane3 ========
                {
                    scrollPane3.setBorder(new EtchedBorder());

                    //---- list2 ----
                    list2.setBorder(new EtchedBorder());
                    scrollPane3.setViewportView(list2);
                }

                //---- logroTextLabel2 ----
                logroTextLabel2.setText("Friends");

                GroupLayout groupLayout = new GroupLayout(group);
                group.setLayout(groupLayout);
                groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup()
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(groupLayout.createParallelGroup()
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(groupLayout.createParallelGroup()
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
                            .addGap(131, 131, 131)
                            .addGroup(groupLayout.createParallelGroup()
                                .addComponent(logroTextLabel2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(logroList, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(200, Short.MAX_VALUE))
                );
                groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup()
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button2))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(31, 31, 31)
                                    .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(logroList, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                    .addComponent(logroTextLabel2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
                            .addGap(43, 43, 43))
                );
            }
            profile.setViewportView(group);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profile, GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profile, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
    private JScrollPane profile;
    private JPanel group;
    private JLabel profileImageLabel;
    private JLabel usernameNameLabel;
    private JLabel descriptionLabel;
    private JButton button2;
    private JLabel logroTextLabel;
    private JList logroList;
    private JPanel LastPlayedPanel;
    private JLabel lastGameNameLabel;
    private JLabel lastGameImage2;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JList list1;
    private JScrollPane scrollPane3;
    private JList list2;
    private JLabel logroTextLabel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
