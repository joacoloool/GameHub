    /*
     * Created by JFormDesigner on Fri Nov 15 03:50:34 ART 2024
     */
    package com.gamehub.gui;

    import java.awt.event.*;

    import com.gamehub.gui.utilities.AchievementCellRender;
    import com.gamehub.gui.utilities.GameCellRender;
    import com.gamehub.managers.Manager;
    import com.gamehub.models.Achievement;
    import com.gamehub.models.Game;
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

        private final User currentUser;
        private final Manager manager;


        public ProfileGui(Manager manager, User user) {
            initComponents();
            this.manager = manager;
            this.currentUser = user;
            achievementDefaultListModel = new DefaultListModel<>();
            feedListModel = new DefaultListModel<>();
            updateProfile(currentUser, manager);
            achievmentList.setCellRenderer(new AchievementCellRender());
        }


        ///////////////////TOM///////////////////

        protected void updateProfile(User user, Manager manager) {
            usernameNameLabel.setText(user.getName());
            descriptionLabel.setText(user.getDescription());
            Icon profileIcon = user.getProfileImage();
            if (profileIcon != null) {
                profileImageL.setIcon(upscaleIco(profileIcon, 179, 163));
            }
            // Actualizar logros
            achievementDefaultListModel.clear();
            manager.verifyAchievements();
            for (Achievement achievement : user.getMyAchievements()) {
                achievementDefaultListModel.addElement(achievement);
            }
            achievmentList.setModel(achievementDefaultListModel);

            // Actualizar el feed de actividades
            feedListModel.clear();
            ArrayList<Post> activities = user.getFeed().getPosts();
            for (Post activity : activities) {
                feedListModel.addElement(activity);
            }
            feedList.setModel(feedListModel);


            if (!achievementDefaultListModel.isEmpty()) {
                lastAchievementImage.setVisible(true);
                lastAchievementImage.setIcon(achievementDefaultListModel.lastElement().getIcon());
                lastAchievementName.setText(achievementDefaultListModel.lastElement().getName());

            }

            try {
                Game game = user.getLastPlayed();
                lastPlayedPanel.setVisible(true);
                lastGameImage.setIcon(game.getIcon());
                lastGameInfo.setText(game.getDescription());
                lastGameL.setText(game.getTitle());
                lastGameImage.setIcon(upscaleIco(game.getHeader(), lastGameImage.getWidth(), lastGameImage.getHeight()));

            } catch (NullPointerException e) {
                lastPlayedPanel.setVisible(false);
            }

        }

        /////////////////////////////////////////

        private void modifyProfile(ActionEvent e) {
            textField2.setText(currentUser.getName());
            textField3.setText(currentUser.getDescription());

            dialog2.setVisible(true);
            button3.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              String newName = textField2.getText();
                                              String newDescription = textField3.getText();

                                              currentUser.setName(newName);
                                              currentUser.setDescription(newDescription);

                                              updateProfile(currentUser, manager);
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
                                              String postContent = textField1.getText();

                                              if (!postContent.trim().isEmpty()) {
                                                  Post newPost = new Post(postContent);
                                                  feedListModel.addElement(newPost);
                                                  currentUser.getFeed().createPost(postContent);
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

        private void modifyProfileButtonMouseClicked(MouseEvent e) {
            // TODO add your code here
        }

        private void achievmentListMouseEntered(MouseEvent e) {
            Achievement ach = (Achievement) achievmentList.getSelectedValue();
            if (ach != null) {
                // Muestra el diálogo
                infoAchievDial.setVisible(true);

                // Configura los textos del diálogo
                achieNameL.setText(ach.getName());
                achieInfoL.setText(ach.getDescription()); // Para manejar texto largo

                // Posiciona el diálogo cerca del mouse
                try {
                    infoAchievDial.setLocation(
                            e.getXOnScreen() + 10,
                            e.getYOnScreen() + 10
                    );
                } catch (Exception ex) {

                }

            }
        }

        private void achievmentListMouseExited(MouseEvent e) {
            infoAchievDial.setVisible(false);
        }

        private void achievmentListMouseClicked(MouseEvent e) {
            achievmentListMouseEntered(e);
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
            profile = new JScrollPane();
            group = new JPanel();
            usernameNameLabel = new JLabel();
            descriptionLabel = new JLabel();
            modifyProfileButton = new JButton();
            logroTextLabel = new JLabel();
            friendScrollPanel = new JScrollPane();
            friendList = new JList();
            FriendsTextLabel = new JLabel();
            profileImageL = new JLabel();
            lastPlayedPanel = new JPanel();
            lastGameImage = new JLabel();
            scrollPane3 = new JScrollPane();
            lastGameInfo = new JTextPane();
            lastGameL = new JLabel();
            feedScrollPanel = new JScrollPane();
            feedList = new JList();
            createPostButton = new JButton();
            scrollPane1 = new JScrollPane();
            achievmentList = new JList();
            lastAchievementImage = new JLabel();
            lastAchievementName = new JLabel();
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
            infoAchievDial = new JPopupMenu();
            achieNameL = new JLabel();
            scrollPane2 = new JScrollPane();
            achieInfoL = new JTextPane();

            //======== this ========
            setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );

            //======== profile ========
            {
                profile.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //======== group ========
                {
                    group.setFont(group.getFont().deriveFont(group.getFont().getSize() + 13f));
                    group.setBorder(new LineBorder(new Color(0x20000000, true), 7));

                    //---- usernameNameLabel ----
                    usernameNameLabel.setText("Username");
                    usernameNameLabel.setFont(usernameNameLabel.getFont().deriveFont(usernameNameLabel.getFont().getSize() + 8f));
                    usernameNameLabel.setBorder(null);
                    usernameNameLabel.setFocusable(false);
                    usernameNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

                    //---- descriptionLabel ----
                    descriptionLabel.setText("//DESCRIPTION");
                    descriptionLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
                    descriptionLabel.setBorder(null);
                    descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    descriptionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

                    //---- modifyProfileButton ----
                    modifyProfileButton.setText("Modificar Perfil");
                    modifyProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    modifyProfileButton.addActionListener(e -> modifyProfile(e));
                    modifyProfileButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            modifyProfileButtonMouseClicked(e);
                        }
                    });

                    //---- logroTextLabel ----
                    logroTextLabel.setText("Badges");
                    logroTextLabel.setFont(logroTextLabel.getFont().deriveFont(logroTextLabel.getFont().getSize() + 2f));

                    //======== friendScrollPanel ========
                    {
                        friendScrollPanel.setBorder(new EtchedBorder());

                        //---- friendList ----
                        friendList.setBorder(new LineBorder(new Color(0x34000000, true), 2));
                        friendList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        friendScrollPanel.setViewportView(friendList);
                    }

                    //---- FriendsTextLabel ----
                    FriendsTextLabel.setText("Friends");
                    FriendsTextLabel.setFont(FriendsTextLabel.getFont().deriveFont(FriendsTextLabel.getFont().getSize() + 2f));

                    //---- profileImageL ----
                    profileImageL.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/defaultProfilePic.jpg")));
                    profileImageL.setHorizontalAlignment(SwingConstants.CENTER);
                    profileImageL.setBorder(new LineBorder(new Color(0x1a99eb), 3));

                    //======== lastPlayedPanel ========
                    {
                        lastPlayedPanel.setBorder(new TitledBorder(new LineBorder(new Color(0x34000000, true), 30), "Last Played", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));
                        lastPlayedPanel.setFont(lastPlayedPanel.getFont().deriveFont(lastPlayedPanel.getFont().getStyle() | Font.BOLD, lastPlayedPanel.getFont().getSize() + 5f));

                        //---- lastGameImage ----
                        lastGameImage.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\UTN_logo.jpg"));
                        lastGameImage.setBorder(new EtchedBorder());
                        lastGameImage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

                        //======== scrollPane3 ========
                        {

                            //---- lastGameInfo ----
                            lastGameInfo.setEditable(false);
                            lastGameInfo.setFocusable(false);
                            lastGameInfo.setBorder(null);
                            lastGameInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                            scrollPane3.setViewportView(lastGameInfo);
                        }

                        //---- lastGameL ----
                        lastGameL.setText("lastGameL");
                        lastGameL.setFont(lastGameL.getFont().deriveFont(lastGameL.getFont().getStyle() & ~Font.BOLD, lastGameL.getFont().getSize() + 7f));
                        lastGameL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

                        GroupLayout lastPlayedPanelLayout = new GroupLayout(lastPlayedPanel);
                        lastPlayedPanel.setLayout(lastPlayedPanelLayout);
                        lastPlayedPanelLayout.setHorizontalGroup(
                            lastPlayedPanelLayout.createParallelGroup()
                                .addGroup(lastPlayedPanelLayout.createSequentialGroup()
                                    .addComponent(lastGameImage, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(lastPlayedPanelLayout.createParallelGroup()
                                        .addGroup(lastPlayedPanelLayout.createSequentialGroup()
                                            .addComponent(lastGameL, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 32, Short.MAX_VALUE))
                                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)))
                        );
                        lastPlayedPanelLayout.setVerticalGroup(
                            lastPlayedPanelLayout.createParallelGroup()
                                .addGroup(lastPlayedPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lastGameL, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addComponent(lastGameImage, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        );
                    }

                    //======== feedScrollPanel ========
                    {

                        //---- feedList ----
                        feedList.setBorder(new TitledBorder(new MatteBorder(30, 1, 1, 1, new Color(0x35000000, true)), "Comments", TitledBorder.LEFT, TitledBorder.TOP,
                            new Font("Inter", Font.PLAIN, 14), Color.white));
                        feedList.setFont(feedList.getFont().deriveFont(feedList.getFont().getSize() + 1f));
                        feedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        feedList.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                        feedScrollPanel.setViewportView(feedList);
                    }

                    //---- createPostButton ----
                    createPostButton.setText("Create Post");
                    createPostButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    createPostButton.addActionListener(e -> createPost(e));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane1.setFocusable(false);

                        //---- achievmentList ----
                        achievmentList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        achievmentList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                achievmentListMouseClicked(e);
                            }
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                achievmentListMouseEntered(e);
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                achievmentListMouseExited(e);
                            }
                        });
                        scrollPane1.setViewportView(achievmentList);
                    }

                    //---- lastAchievementImage ----
                    lastAchievementImage.setBorder(null);

                    //---- lastAchievementName ----
                    lastAchievementName.setFont(lastAchievementName.getFont().deriveFont(lastAchievementName.getFont().getStyle() | Font.BOLD));
                    lastAchievementName.setAlignmentY(-6.5F);

                    GroupLayout groupLayout = new GroupLayout(group);
                    group.setLayout(groupLayout);
                    groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(createPostButton))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(profileImageL, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addGroup(groupLayout.createParallelGroup()
                                            .addComponent(usernameNameLabel)
                                            .addComponent(descriptionLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(lastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(feedScrollPanel))
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(modifyProfileButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addGap(167, 167, 167))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup()
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(lastAchievementImage, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lastAchievementName, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addGroup(groupLayout.createParallelGroup()
                                                    .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(friendScrollPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))))
                                        .addGap(115, 115, 115))))
                    );
                    groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(usernameNameLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(modifyProfileButton))
                                        .addGroup(groupLayout.createParallelGroup()
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(lastAchievementName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(lastAchievementImage, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(profileImageL, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(lastPlayedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(groupLayout.createParallelGroup()
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(createPostButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(feedScrollPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(friendScrollPanel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
                                .addGap(401, 401, 401))
                    );
                }
                profile.setViewportView(group);
            }

            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profile, GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                        .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup()
                    .addComponent(profile, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
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

            //======== infoAchievDial ========
            {

                //---- achieNameL ----
                achieNameL.setText("ASDSAAASDADS");
                infoAchievDial.add(achieNameL);

                //======== scrollPane2 ========
                {

                    //---- achieInfoL ----
                    achieInfoL.setText("asdadsasdasd");
                    scrollPane2.setViewportView(achieInfoL);
                }
                infoAchievDial.add(scrollPane2);
            }
            // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        private JScrollPane profile;
        private JPanel group;
        private JLabel usernameNameLabel;
        private JLabel descriptionLabel;
        private JButton modifyProfileButton;
        private JLabel logroTextLabel;
        private JScrollPane friendScrollPanel;
        private JList friendList;
        private JLabel FriendsTextLabel;
        private JLabel profileImageL;
        private JPanel lastPlayedPanel;
        private JLabel lastGameImage;
        private JScrollPane scrollPane3;
        private JTextPane lastGameInfo;
        private JLabel lastGameL;
        private JScrollPane feedScrollPanel;
        private JList feedList;
        private JButton createPostButton;
        private JScrollPane scrollPane1;
        private JList achievmentList;
        private JLabel lastAchievementImage;
        private JLabel lastAchievementName;
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
        private JPopupMenu infoAchievDial;
        private JLabel achieNameL;
        private JScrollPane scrollPane2;
        private JTextPane achieInfoL;
        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
