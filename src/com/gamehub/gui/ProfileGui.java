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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;
    import javax.swing.GroupLayout;
    import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;


    /**
     * @author joaal
     */
    public class ProfileGui extends JPanel {

        DefaultListModel<Post> feedListModel;
        DefaultListModel<Achievement> achievementDefaultListModel;


        public ProfileGui(Manager manager, User user) {
            initComponents();
            this.manager = manager;
            this.currentUser  = user;
            achievementDefaultListModel = new DefaultListModel<>();
            feedListModel = new DefaultListModel<>();
            updateProfile(currentUser , manager);
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


        protected void updateProfile(User user, Manager manager) {
            usernameNameLabel.setText(user.getName());
            descriptionLabel.setText(user.getDescription());

            Icon profileIcon = user.getProfileImage();
            if (profileIcon != null) {
                profileImageLabel.setIcon(upscaleIco(profileIcon, 170, 160));
            } else {
                // Si no hay imagen, puedes establecer una imagen por defecto
                profileImageLabel.setIcon(upscaleIco(new ImageIcon("ruta/a/imagen/por/defecto.jpg"), 170, 160));
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

        private void createPostButtonMouseClicked(MouseEvent e) {
            // TODO add your code here
        }
        private void modifyProfile(ActionEvent e) {
            JDialog editProfileDialog = new JDialog((Frame)null, "Editar Perfil", true);
            editProfileDialog.setSize(700, 700);
            editProfileDialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            editProfileDialog.add(panel);

            JLabel profileImageLabel = this.profileImageLabel;
            panel.add(profileImageLabel);

            JButton changeImageButton = new JButton("Cambiar Imagen");
            panel.add(changeImageButton);

            JLabel nameLabel = new JLabel("Cambiar Nombre:");
            JTextField nameTextField = new JTextField(currentUser .getName()); // Usar el nombre actual
            nameTextField.setPreferredSize(new Dimension(200, 30));

            JPanel namePanel = new JPanel();
            namePanel.add(nameLabel);
            namePanel.add(nameTextField);
            panel.add(namePanel);

            panel.add(Box.createVerticalStrut(1));

            JLabel descriptionLabel = new JLabel("Cambiar Descripción:");
            JTextField descriptionTextField = new JTextField(currentUser .getDescription()); // Usar la descripción actual
            descriptionTextField.setPreferredSize(new Dimension(200, 30));

            JPanel descriptionPanel = new JPanel();
            descriptionPanel.add(descriptionLabel);
            descriptionPanel.add(descriptionTextField);
            panel.add(descriptionPanel);

            JButton applyChangesButton = new JButton("Aplicar Cambios");
            panel.add(applyChangesButton);

            final ImageIcon[] selectedIcon = new ImageIcon[1];

            changeImageButton.addActionListener(e1 -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar imagen de perfil");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png"));

                int returnValue = fileChooser.showOpenDialog(editProfileDialog);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    selectedIcon[0] = new ImageIcon(selectedImagePath);
                    profileImageLabel.setIcon(upscaleIco(selectedIcon[0], 170, 160));
                }
            });

            applyChangesButton.addActionListener(e1 -> {
                String newName = nameTextField.getText().trim();
                String newDescription = descriptionTextField.getText().trim();
                if (!newName.isEmpty()) {
                    currentUser .setName(newName); // Modificar el nombre del usuario existente
                    currentUser .setDescription(newDescription); // Modificar la descripción

                    if (selectedIcon[0] != null) {
                        currentUser .setProfileImage(selectedIcon[0]); // Cambiar la imagen de perfil
                    }

                    JOptionPane.showMessageDialog(editProfileDialog, "Cambios aplicados exitosamente.");
                    updateProfile(currentUser , manager); // Actualizar la interfaz de usuario
                    editProfileDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(editProfileDialog, "Por favor, ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            editProfileDialog.setVisible(true);
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
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
            setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",TitledBorder.CENTER,TitledBorder.BOTTOM,new Font("Dia\u006cog",Font.BOLD,
            12),Color.red), getBorder())); addPropertyChangeListener(new PropertyChangeListener(){@Override public void propertyChange(PropertyChangeEvent e){if("bord\u0065r".equals(e.
            getPropertyName()))throw new RuntimeException();}});

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
                    modifyProfileButton.addActionListener(e -> modifyProfile(e));

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
        // Generated using JFormDesigner Evaluation license - Gabriel Tomas Delio
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
        private User currentUser;
        private Manager manager;;


        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
