    /*
     * Created by JFormDesigner on Fri Nov 15 03:50:34 ART 2024
     */
    package com.gamehub.gui;
    import java.awt.event.*;

    import com.gamehub.exceptions.NonExistObjectException;
    import com.gamehub.exceptions.UyMeLlameAmiMismoException;
    import com.gamehub.gui.utilities.AchievementCellRender;
    import com.gamehub.managers.Manager;
    import com.gamehub.models.Achievement;
    import com.gamehub.models.Game;
    import com.gamehub.models.Post;
    import com.gamehub.models.User;
    import java.awt.*;
    import java.io.File;
    import java.util.ArrayList;
    import javax.swing.*;
    import javax.swing.GroupLayout;
    import javax.swing.border.*;
    import javax.swing.filechooser.FileNameExtensionFilter;

    import com.gamehub.utils.ImageFormatter;
    import net.miginfocom.swing.*;
    import static com.gamehub.utils.ImageFormatter.*;

    /**
     * @author joaal
     */
    public class ProfileGui extends JPanel {

        DefaultListModel<Post> feedListModel;
        DefaultListModel<Achievement> achievementDefaultListModel;
        DefaultListModel<User> friendDefaultListModel;
        private final User currentUser;
        private final Manager manager;


        public ProfileGui(Manager manager, User user) {
            initComponents();
            this.manager = manager;
            this.currentUser = user;
            achievementDefaultListModel = new DefaultListModel<>();
            feedListModel = new DefaultListModel<>();
            friendDefaultListModel = new DefaultListModel<>();
            updateProfile(manager);
            achievmentList.setCellRenderer(new AchievementCellRender());
        }


        protected void updateProfile(Manager manager) {

            //USERNAME
            if (currentUser.getNickname().isEmpty()) {
                usernameNameLabel.setText(currentUser.getName());
            } else {
                usernameNameLabel.setText(currentUser.getNickname());
            }
            //DESCRIPTION
            descriptionLabel.setText(currentUser.getDescription());

            //ICON
            Icon profileIcon = currentUser.getProfileImage();
            if (profileIcon != null) {
                profileImageL.setIcon(upscaleIco(profileIcon, profileImageL.getWidth(), profileImageL.getHeight()));
            }

            // Actualizar logros
            achievementDefaultListModel.clear();
            manager.verifyAchievements();
            for (Achievement achievement : currentUser.getMyAchievements()) {
                achievementDefaultListModel.addElement(achievement);
            }
            achievmentList.setModel(achievementDefaultListModel);

            // Actualizar el feed de actividades
            feedListModel.clear();
            ArrayList<Post> activities = currentUser.getFeed().getPosts();
            for (Post activity : activities) {
                feedListModel.addElement(activity);
            }
            feedList.setModel(feedListModel);

            //Actualiza friends
            friendDefaultListModel.clear();
            for (User user: manager.getUsers())
            {
               if (currentUser.searchFriend(user.getName()))
               {
                   friendDefaultListModel.addElement(user);
               }
            }
            friendList.setModel(friendDefaultListModel);

            if (!achievementDefaultListModel.isEmpty()) {
                lastAchievementImage.setVisible(true);
                lastAchievementImage.setIcon(achievementDefaultListModel.lastElement().getIcon());
                lastAchievementName.setText(achievementDefaultListModel.lastElement().getName());

            }

            try {
                Game game = currentUser.getLastPlayed();
                lastPlayedPanel.setVisible(true);
                lastGameInfo.setText(game.getDescription());
                lastGameL.setText(game.getTitle());
                lastGameImage.setIcon(upscaleIco(game.getHeader(),lastGameImage.getWidth(),lastGameImage.getHeight()));

            } catch (NullPointerException e) {
                lastPlayedPanel.setVisible(false);
            };

        }

        /////////////////////////////////////////

        private void modifyProfileButtonMouseClicked(MouseEvent e) {
            modifyNameField.setText(currentUser.getNickname());
            modifyDescriptionField.setText(currentUser.getDescription());
            imageChossed.setIcon(currentUser.getProfileImage());
            dialog2.setVisible(true);
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

        private void applyButtonMouseClicked(MouseEvent e) {
            currentUser.setDescription(modifyDescriptionField.getText());
            currentUser.setNickname(modifyNameField.getText());
            try {
                currentUser.setProfileImage(ImageFormatter.upscaleIco(imageChossed.getIcon(), profileImageL.getWidth(), profileImageL.getHeight()));
            } catch (NullPointerException f) {
                System.out.println("No se cambio la foto");
            }

            dialog2.setVisible(false);
            updateProfile(manager);
        }

        private void searchFileButtonMouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            // Configurar el filtro para archivos PNG
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PNG", "png");
            fileChooser.setFileFilter(filter);

            // Abrir el cuadro de diálogo
            int returnValue = fileChooser.showOpenDialog(null);

            // Verificar si el usuario seleccionó un archivo
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Obtener el archivo seleccionado
                File selectedFile = fileChooser.getSelectedFile();
                imageChossed.setIcon(getIconFromFile(selectedFile));
            }
        }

        private void cancelButtonMouseClicked(MouseEvent e) {
            dialog2.setVisible(false);
        }

        private void createPostButtonMouseClicked(MouseEvent e) {
           dialog1.setVisible(true);
        }

        private void postCancelButtonMouseClicked(MouseEvent e) {
            dialog1.setVisible(false);
        }

        private void postButtonMouseClicked(MouseEvent e) {
        currentUser.createPost(postField.getText());
        dialog1.setVisible(false);
        updateProfile(manager);
        }

        private void addFriendButtonMouseClicked(MouseEvent e) {
            addFriendDialog.setVisible(true);
            addFriendDialog.setModal(true);
        }

        private void addButtonFriendMouseClicked(MouseEvent e) {
            try {
                if (manager.containsUser(new User(addFriendField.getText()))) {
                    try {
                        currentUser.addFriend(addFriendField.getText());
                    }
                    catch (UyMeLlameAmiMismoException e1){
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }catch (NonExistObjectException e1){
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }finally {
                addFriendDialog.dispose();
                updateProfile(manager);
                System.out.println(currentUser.getFriends());
            }


        }

        private void cancelButtonFriendMouseClicked(MouseEvent e) {
            addFriendDialog.dispose();
        }



        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
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
            addFriendButton = new JButton();
            dialog2 = new JDialog();
            label1 = new JLabel();
            modifyNameField = new JTextField();
            label2 = new JLabel();
            modifyDescriptionField = new JTextField();
            label3 = new JLabel();
            imageChossed = new JLabel();
            searchFileButton = new JButton();
            cancelButton = new JButton();
            applyButton = new JButton();
            infoAchievDial = new JPopupMenu();
            achieNameL = new JLabel();
            scrollPane2 = new JScrollPane();
            achieInfoL = new JTextPane();
            dialog1 = new JDialog();
            scrollPane4 = new JScrollPane();
            postField = new JTextPane();
            postCancelButton = new JButton();
            postButton = new JButton();
            addFriendDialog = new JDialog();
            addFriendField = new JTextField();
            friendLabel = new JLabel();
            addButtonFriend = new JButton();
            cancelButtonFriend = new JButton();

            //======== this ========
            setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.
            awt.Font.BOLD,12),java.awt.Color.red), getBorder()))
            ; addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;

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
                    modifyProfileButton.setText("Modify Profile");
                    modifyProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                                    .addComponent(scrollPane3)
                                    .addContainerGap())
                                .addComponent(lastGameImage, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
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
                    createPostButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            createPostButtonMouseClicked(e);
                        }
                    });

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

                    //---- addFriendButton ----
                    addFriendButton.setText("Add Friend");
                    addFriendButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            addFriendButtonMouseClicked(e);
                        }
                    });

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
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(logroTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                            .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(addFriendButton))
                                                        .addComponent(friendScrollPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))))
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
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(FriendsTextLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(addFriendButton))
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

            //======== dialog2 ========
            {
                dialog2.setTitle("Modify Profile");
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
                label1.setText("Change name");
                dialog2ContentPane.add(label1, "cell 2 1");
                dialog2ContentPane.add(modifyNameField, "cell 2 2 11 1");

                //---- label2 ----
                label2.setText("Change Description");
                dialog2ContentPane.add(label2, "cell 2 5");
                dialog2ContentPane.add(modifyDescriptionField, "cell 2 7 11 1");

                //---- label3 ----
                label3.setText("Change Image");
                dialog2ContentPane.add(label3, "cell 2 9");

                //---- imageChossed ----
                imageChossed.setText("text");
                imageChossed.setVisible(false);
                imageChossed.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/defaultProfilePic.jpg")));
                dialog2ContentPane.add(imageChossed, "cell 3 10 8 3");

                //---- searchFileButton ----
                searchFileButton.setText("Search File...");
                searchFileButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        searchFileButtonMouseClicked(e);
                    }
                });
                dialog2ContentPane.add(searchFileButton, "cell 2 11");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                        cancelButtonMouseClicked(e);
                    }
                });
                dialog2ContentPane.add(cancelButton, "cell 2 18");

                //---- applyButton ----
                applyButton.setText("Apply changes");
                applyButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        applyButtonMouseClicked(e);
                    }
                });
                dialog2ContentPane.add(applyButton, "cell 12 18");
                dialog2.pack();
                dialog2.setLocationRelativeTo(dialog2.getOwner());
            }

            //======== infoAchievDial ========
            {

                //---- achieNameL ----
                achieNameL.setText(".");
                infoAchievDial.add(achieNameL);

                //======== scrollPane2 ========
                {

                    //---- achieInfoL ----
                    achieInfoL.setText(".");
                    scrollPane2.setViewportView(achieInfoL);
                }
                infoAchievDial.add(scrollPane2);
            }

            //======== dialog1 ========
            {
                var dialog1ContentPane = dialog1.getContentPane();

                //======== scrollPane4 ========
                {

                    //---- postField ----
                    postField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    scrollPane4.setViewportView(postField);
                }

                //---- postCancelButton ----
                postCancelButton.setText("Cancel");
                postCancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        postCancelButtonMouseClicked(e);
                    }
                });

                //---- postButton ----
                postButton.setText("Post");
                postButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        postButtonMouseClicked(e);
                    }
                });

                GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
                dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
                dialog1ContentPaneLayout.setHorizontalGroup(
                    dialog1ContentPaneLayout.createParallelGroup()
                        .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(postCancelButton)
                            .addGap(158, 158, 158)
                            .addComponent(postButton)
                            .addContainerGap(56, Short.MAX_VALUE))
                        .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                );
                dialog1ContentPaneLayout.setVerticalGroup(
                    dialog1ContentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                .addComponent(postCancelButton)
                                .addComponent(postButton))
                            .addGap(18, 18, 18))
                );
                dialog1.pack();
                dialog1.setLocationRelativeTo(dialog1.getOwner());
            }

            //======== addFriendDialog ========
            {
                addFriendDialog.setTitle("Add Friend");
                var addFriendDialogContentPane = addFriendDialog.getContentPane();

                //---- friendLabel ----
                friendLabel.setText("Friend");

                //---- addButtonFriend ----
                addButtonFriend.setText("Add");
                addButtonFriend.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addButtonFriendMouseClicked(e);
                    }
                });

                //---- cancelButtonFriend ----
                cancelButtonFriend.setText("Cancel");
                cancelButtonFriend.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonFriendMouseClicked(e);
                    }
                });

                GroupLayout addFriendDialogContentPaneLayout = new GroupLayout(addFriendDialogContentPane);
                addFriendDialogContentPane.setLayout(addFriendDialogContentPaneLayout);
                addFriendDialogContentPaneLayout.setHorizontalGroup(
                    addFriendDialogContentPaneLayout.createParallelGroup()
                        .addGroup(addFriendDialogContentPaneLayout.createSequentialGroup()
                            .addContainerGap(15, Short.MAX_VALUE)
                            .addGroup(addFriendDialogContentPaneLayout.createParallelGroup()
                                .addComponent(friendLabel)
                                .addComponent(addFriendField, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18))
                        .addGroup(GroupLayout.Alignment.TRAILING, addFriendDialogContentPaneLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(cancelButtonFriend)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                            .addComponent(addButtonFriend)
                            .addGap(34, 34, 34))
                );
                addFriendDialogContentPaneLayout.setVerticalGroup(
                    addFriendDialogContentPaneLayout.createParallelGroup()
                        .addGroup(addFriendDialogContentPaneLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(friendLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addFriendField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(addFriendDialogContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(addButtonFriend)
                                .addComponent(cancelButtonFriend))
                            .addContainerGap(19, Short.MAX_VALUE))
                );
                addFriendDialog.pack();
                addFriendDialog.setLocationRelativeTo(addFriendDialog.getOwner());
            }
            // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
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
        private JButton addFriendButton;
        private JDialog dialog2;
        private JLabel label1;
        private JTextField modifyNameField;
        private JLabel label2;
        private JTextField modifyDescriptionField;
        private JLabel label3;
        private JLabel imageChossed;
        private JButton searchFileButton;
        private JButton cancelButton;
        private JButton applyButton;
        private JPopupMenu infoAchievDial;
        private JLabel achieNameL;
        private JScrollPane scrollPane2;
        private JTextPane achieInfoL;
        private JDialog dialog1;
        private JScrollPane scrollPane4;
        private JTextPane postField;
        private JButton postCancelButton;
        private JButton postButton;
        private JDialog addFriendDialog;
        private JTextField addFriendField;
        private JLabel friendLabel;
        private JButton addButtonFriend;
        private JButton cancelButtonFriend;
        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
