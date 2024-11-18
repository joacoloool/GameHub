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

import net.miginfocom.swing.*;

import static com.gamehub.utils.ImageFormatter.upscaleIco;


    /**
     * @author joaal
     */
    public class ProfileGui extends JPanel {

        DefaultListModel<Post> feedListModel;
        DefaultListModel<Achievement> achievementDefaultListModel;
        private User currentUser;
        private Manager manager;


        public ProfileGui(Manager manager, User user) {
            initComponents();
            this.manager = manager;
            this.currentUser  = user;
            achievementDefaultListModel = new DefaultListModel<>();
            feedListModel = new DefaultListModel<>();
            updateProfile(currentUser , manager);


        }



        protected void updateProfile(User user, Manager manager) {
            usernameNameLabel.setText(user.getName());
            descriptionLabel.setText(user.getDescription());

            Icon profileIcon = user.getProfileImage();
            if (profileIcon != null) {
              //  profileImageLabel.setIcon(upscaleIco(profileIcon, 170, 160));
            } else {
                // Si no hay imagen, puedes establecer una imagen por defecto
              // profileImageLabel.setIcon(upscaleIco(new ImageIcon("ruta/a/imagen/por/defecto.jpg"), 170, 160));
            }

            // Actualizar logros
            achievementDefaultListModel.clear(); // Limpiar el modelo antes de agregar nuevos elementos
            manager.verifyAchievements();
            for (Achievement achievement : user.getMyAchievements()) {
                achievementDefaultListModel.addElement(achievement);
            }
            achievmentList.setModel(achievementDefaultListModel);

            // Actualizar el feed de actividades
            feedListModel.clear(); // Limpiar el modelo antes de agregar nuevos elementos
            ArrayList<Post> activities = user.getFeed().getPosts();
            for (Post activity : activities) {
                feedListModel.addElement(activity);
            }
            feedList.setModel(feedListModel);
        }

        private void modifyProfile(ActionEvent e) {
            textField2.setText(currentUser .getName());
            textField3.setText(currentUser.getDescription());

            dialog2.setVisible(true);
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName=textField2.getText();
                    String newDescription=textField3.getText();

                    currentUser.setName(newName);
                    currentUser.setDescription(newDescription);

                    updateProfile(currentUser,manager);

                    dialog2.dispose();
                }
            }
            );
        }

        private void createPost(ActionEvent e) {
            dialog1.setVisible(true);

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String postContent=textField1.getText();

                    if (!postContent.trim().isEmpty()) {
                        Post newPost = new Post(postContent);
                        feedListModel.addElement(newPost);
                        currentUser .getFeed().createPost(postContent);
                        dialog1.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog1, "El contenido del post no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            );
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog1.dispose();
                }
            });
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
            profile = new JScrollPane();
            group = new JPanel();
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
            label4 = new JLabel();
            dialog1 = new JDialog();
            textField1 = new JTextField();
            button2 = new JButton();
            button1 = new JButton();
            dialog2 = new JDialog();
            label1 = new JLabel();
            textField2 = new JTextField();
            label2 = new JLabel();
            textField3 = new JTextField();
            label3 = new JLabel();
            button5 = new JButton();
            button4 = new JButton();
            button3 = new JButton();

            //======== this ========
            setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );

            //======== profile ========
            {
                profile.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //======== group ========
                {
                    group.setFont(group.getFont().deriveFont(group.getFont().getSize() + 13f));

                    //---- usernameNameLabel ----
                    usernameNameLabel.setText("Username");
                    usernameNameLabel.setFont(usernameNameLabel.getFont().deriveFont(usernameNameLabel.getFont().getSize() + 8f));
                    usernameNameLabel.setBorder(new EtchedBorder());

                    //---- descriptionLabel ----
                    descriptionLabel.setText("//DESCRIPTION");
                    descriptionLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
                    descriptionLabel.setBorder(new EtchedBorder());

                    //---- modifyProfileButton ----
                    modifyProfileButton.setText("Modificar Perfil");
                    modifyProfileButton.addActionListener(e -> modifyProfile(e));

                    //---- logroTextLabel ----
                    logroTextLabel.setText("Badges");

                    //---- achievmentList ----
                    achievmentList.setBorder(new EtchedBorder());

                    //======== LastPlayedPanel ========
                    {
                        LastPlayedPanel.setBorder(new TitledBorder(new LineBorder(new Color(0x34000000, true), 10), "Last Played", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));

                        //---- lastGameNameLabel ----
                        lastGameNameLabel.setText("Dragon Ball Z");
                        lastGameNameLabel.setFont(lastGameNameLabel.getFont().deriveFont(lastGameNameLabel.getFont().getStyle() & ~Font.BOLD, lastGameNameLabel.getFont().getSize() + 6f));

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
                    feedNameLabel.setText("Comments");
                    feedNameLabel.setFont(new Font("JetBrains Mono", feedNameLabel.getFont().getStyle() & ~Font.ITALIC, 15));

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
                    createPostButton.addActionListener(e -> createPost(e));

                    //---- label4 ----
                    label4.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/defaultProfilePic.jpg")));
                    label4.setHorizontalAlignment(SwingConstants.CENTER);

                    GroupLayout groupLayout = new GroupLayout(group);
                    group.setLayout(groupLayout);
                    groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(206, 206, 206)
                                        .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(feedNameLabel)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(createPostButton))
                                        .addComponent(feedScrollPanel, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup()
                                            .addComponent(achievmentList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup()
                                                    .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(friendScrollPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap(186, Short.MAX_VALUE))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(modifyProfileButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(266, Short.MAX_VALUE))))
                    );
                    groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(modifyProfileButton)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(LastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(createPostButton)
                                            .addComponent(feedNameLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(feedScrollPanel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap(183, Short.MAX_VALUE)
                                        .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(achievmentList, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(profile, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                        .addContainerGap())
            );

            //======== dialog1 ========
            {
                dialog1.setTitle("Crear Post");
                var dialog1ContentPane = dialog1.getContentPane();
                dialog1ContentPane.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));
                dialog1ContentPane.add(textField1, "cell 0 0 13 6");

                //---- button2 ----
                button2.setText("Cancelar");
                dialog1ContentPane.add(button2, "cell 2 6");

                //---- button1 ----
                button1.setText("Publicar");
                dialog1ContentPane.add(button1, "cell 11 6");
                dialog1.pack();
                dialog1.setLocationRelativeTo(dialog1.getOwner());
            }

            //======== dialog2 ========
            {
                dialog2.setTitle("Modificar Perfil");
                var dialog2ContentPane = dialog2.getContentPane();
                dialog2ContentPane.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- label1 ----
                label1.setText("Modificar Nombre");
                dialog2ContentPane.add(label1, "cell 2 1");
                dialog2ContentPane.add(textField2, "cell 2 3 10 1");

                //---- label2 ----
                label2.setText("Cambiar Descripcion");
                dialog2ContentPane.add(label2, "cell 2 5");
                dialog2ContentPane.add(textField3, "cell 2 7 10 1");

                //---- label3 ----
                label3.setText("Cambiar imagen");
                dialog2ContentPane.add(label3, "cell 2 9");

                //---- button5 ----
                button5.setText("Buscar archivo...");
                dialog2ContentPane.add(button5, "cell 2 11");

                //---- button4 ----
                button4.setText("Cancelar");
                dialog2ContentPane.add(button4, "cell 2 18");

                //---- button3 ----
                button3.setText("Aplicar Cambios");
                dialog2ContentPane.add(button3, "cell 11 18");
                dialog2.pack();
                dialog2.setLocationRelativeTo(dialog2.getOwner());
            }
            // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
        // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
        private JScrollPane profile;
        private JPanel group;
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
        private JLabel label4;
        private JDialog dialog1;
        private JTextField textField1;
        private JButton button2;
        private JButton button1;
        private JDialog dialog2;
        private JLabel label1;
        private JTextField textField2;
        private JLabel label2;
        private JTextField textField3;
        private JLabel label3;
        private JButton button5;
        private JButton button4;
        private JButton button3;
        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
