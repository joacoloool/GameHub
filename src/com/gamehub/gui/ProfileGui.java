    /*
     * Created by JFormDesigner on Fri Nov 15 03:50:34 ART 2024
     */

    package com.gamehub.gui;

import java.awt.event.*;
    import com.gamehub.managers.Manager;
    import com.gamehub.models.Achievement;
    import com.gamehub.models.Post;
    import com.gamehub.models.User;

    import java.awt.*;
    import java.util.ArrayList;
    import javax.swing.*;
    import javax.swing.GroupLayout;
    import javax.swing.border.*;


    /**
     * @author joaal
     */
    public class ProfileGui extends JPanel {

        DefaultListModel<Post> feedListModel;
        DefaultListModel<Achievement> achievementDefaultListModel;


        public ProfileGui(Manager manager, User user) {
            initComponents();
            achievementDefaultListModel = new DefaultListModel<>();
            feedListModel = new DefaultListModel<>();
            updateProfile(user,manager);
        }

        public static Icon upscaleIco(Icon icon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        }

        public static Icon upscaleIco(Icon icon, int width, int height) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        }


        protected void updateProfile(User user,Manager manager) {
            // Actualiza la información del perfil con los datos del usuario
            usernameNameLabel.setText(user.getName());
            descriptionLabel.setText(user.getDescription());

            // Escalar y establecer la imagen de perfil
            Icon profileIcon = new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"); // Cambiar según la ruta
            profileImageLabel.setIcon(upscaleIco(profileIcon, 170, 160));

            // Actualizar logros
            achievementDefaultListModel.clear(); // Limpiar el modelo antes de agregar nuevos elementos
            manager.verifyAchievements();
            for (Achievement achievement : user.getMyAchievements()) {
                achievementDefaultListModel.addElement(achievement);
            }
            achievmentList.setModel(achievementDefaultListModel);

            // Actualizar el feed de actividades
            feedListModel.clear(); // Limpiar el modelo antes de agregar nuevos elementos
            ArrayList<Post> activities = user.getFeed().getPosts(); //
            for (Post activity : activities) {
                feedListModel.addElement(activity);
            }
            feedList.setModel(feedListModel);
        }

        private void createPostButtonMouseClicked(MouseEvent e) {
            // TODO add your code here
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
            profile = new JScrollPane();
            group = new JPanel();
            profileImageLabel = new JLabel();
            usernameNameLabel = new JLabel();
            descriptionLabel = new JLabel();
            modifyProfileButton = new JButton();
            logroTextLabel = new JLabel();
            achievmentList = new JList();
            LastPlayedPanel = new JPanel();
            lastGameNameLabel = new JLabel();
            lastGameImage2 = new JLabel();
            feedNameLabel = new JLabel();
            feedScrollPanel = new JScrollPane();
            feedList = new JList();
            friendScrollPanel = new JScrollPane();
            friendList = new JList();
            FriendsTextLabel = new JLabel();
            createPostButton = new JButton();

            //======== this ========
            setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
            0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
            . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
            red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
            beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

            //======== profile ========
            {

                //======== group ========
                {

                    //---- profileImageLabel ----
                    profileImageLabel.setText(".");
                    profileImageLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));
                    profileImageLabel.setBorder(new EtchedBorder());

                    //---- usernameNameLabel ----
                    usernameNameLabel.setText("Username");

                    //---- descriptionLabel ----
                    descriptionLabel.setText("//DESCRIPTION");
                    descriptionLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
                    descriptionLabel.setBorder(new EtchedBorder());

                    //---- modifyProfileButton ----
                    modifyProfileButton.setText("Modificar Perfil");

                    //---- logroTextLabel ----
                    logroTextLabel.setText("Logros");

                    //---- achievmentList ----
                    achievmentList.setBorder(new EtchedBorder());

                    //======== LastPlayedPanel ========
                    {
                        LastPlayedPanel.setBorder(new TitledBorder("Last Played"));

                        //---- lastGameNameLabel ----
                        lastGameNameLabel.setText("Dragon Ball Z");

                        //---- lastGameImage2 ----
                        lastGameImage2.setText(".");
                        lastGameImage2.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));
                        lastGameImage2.setBorder(new EtchedBorder());

                        GroupLayout LastPlayedPanelLayout = new GroupLayout(LastPlayedPanel);
                        LastPlayedPanel.setLayout(LastPlayedPanelLayout);
                        LastPlayedPanelLayout.setHorizontalGroup(
                            LastPlayedPanelLayout.createParallelGroup()
                                .addGroup(LastPlayedPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lastGameImage2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lastGameNameLabel, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(14, Short.MAX_VALUE))
                        );
                        LastPlayedPanelLayout.setVerticalGroup(
                            LastPlayedPanelLayout.createParallelGroup()
                                .addGroup(LastPlayedPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lastGameNameLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(90, Short.MAX_VALUE))
                                .addGroup(LastPlayedPanelLayout.createSequentialGroup()
                                    .addComponent(lastGameImage2, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }

                    //---- feedNameLabel ----
                    feedNameLabel.setText("Feed");
                    feedNameLabel.setFont(new Font("JetBrains Mono", feedNameLabel.getFont().getStyle() & ~Font.ITALIC, 22));

                    //======== feedScrollPanel ========
                    {

                        //---- feedList ----
                        feedList.setBorder(new EtchedBorder());
                        feedScrollPanel.setViewportView(feedList);
                    }

                    //======== friendScrollPanel ========
                    {
                        friendScrollPanel.setBorder(new EtchedBorder());

                        //---- friendList ----
                        friendList.setBorder(new EtchedBorder());
                        friendScrollPanel.setViewportView(friendList);
                    }

                    //---- FriendsTextLabel ----
                    FriendsTextLabel.setText("Friends");

                    //---- createPostButton ----
                    createPostButton.setText("Create Post");
                    createPostButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            createPostButtonMouseClicked(e);
                        }
                    });

                    GroupLayout groupLayout = new GroupLayout(group);
                    group.setLayout(groupLayout);
                    groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addGroup(groupLayout.createParallelGroup()
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(modifyProfileButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(feedNameLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(createPostButton))
                                        .addComponent(feedScrollPanel, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)))
                                .addGap(131, 131, 131)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(achievmentList, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(friendScrollPanel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
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
                                                    .addComponent(modifyProfileButton)
                                                    .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(31, 31, 31)
                                        .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(feedNameLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(createPostButton))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(feedScrollPanel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(achievmentList, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(friendScrollPanel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
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
        private JButton modifyProfileButton;
        private JLabel logroTextLabel;
        private JList achievmentList;
        private JPanel LastPlayedPanel;
        private JLabel lastGameNameLabel;
        private JLabel lastGameImage2;
        private JLabel feedNameLabel;
        private JScrollPane feedScrollPanel;
        private JList feedList;
        private JScrollPane friendScrollPanel;
        private JList friendList;
        private JLabel FriendsTextLabel;
        private JButton createPostButton;
        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
