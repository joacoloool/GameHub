/*
 * Created by JFormDesigner on Thu Nov 14 17:43:38 ART 2024
 */

package com.gamehub.gui;

import com.gamehub.enums.Genre;
import com.gamehub.models.Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Administrator
 */

public class GameGUI extends JDialog {

    private LibraryGUI libraryGUI;

    public GameGUI(Game selectedGame, LibraryGUI libraryGUI) {
        initComponents();
        this.libraryGUI = libraryGUI;
        setLabels(selectedGame);
    }

    public GameGUI(LibraryGUI libraryGUI) {
        initComponents();
        this.libraryGUI = libraryGUI;
    }
    private void cancelButtonMouseClicked(MouseEvent e) {
        closeWindow();
    }

    private void closeWindow() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    private void searchPathButtonMouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            setLabels(chooser.getSelectedFile());
        }
    }

    private void setLabels (Game game) {
        titleField.setText(game.getTitle());
        releaseField.setText(game.getReleaseDate());
        descriptionField.setText(game.getDescription());
        igdbField.setText(game.getAppidIGDB());
        steamField.setText(game.getAppid());
        pathField.setText(game.getPath().getAbsolutePath());
        genreBox.setSelectedItem(game.getGenre());
    }

    private void setLabels(File file){
        pathField.setText(file.getAbsolutePath());
        Game game = new Game(file);
        descriptionField.setText(game.getDescription());
        titleField.setText(game.getTitle());
        releaseField.setText(game.getReleaseDate());
        igdbField.setText(game.getAppidIGDB());
        steamField.setText(game.getAppid());
        genreBox.setSelectedItem(game.getGenre());
    }

    public void okButtonMouseClicked(MouseEvent e) {
        File file = new File(pathField.getText());
        Game game = new Game();
        game.setTitle(titleField.getText());
        game.setReleaseDate(releaseField.getText());
        game.setAppidIGDB(igdbField.getText());
        game.setDescription(descriptionField.getText());
        game.setAppid(steamField.getText());
        game.setPath(new File(pathField.getText()));
        game.setGenre((Genre) genreBox.getSelectedItem());
        game.setIcon(file);
        game.setImages();
        closeWindow();
        if(game.equals(libraryGUI.selectedGame))
        {
            libraryGUI.user.deleteGame(game);
            libraryGUI.gamesListModel.remove(libraryGUI.gamesListModel.indexOf(game));
        }
        libraryGUI.addGame(game);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        pathField = new JTextField();
        searchPathButton = new JButton();
        titleField = new JTextField();
        titleLabel = new JLabel();
        label1 = new JLabel();
        steamField = new JTextField();
        label2 = new JLabel();
        igdbField = new JTextField();
        label3 = new JLabel();
        releaseField = new JTextField();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        descriptionField = new JTextArea();
        label5 = new JLabel();
        genreBox = new JComboBox<>(Genre.values());
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- pathField ----
                pathField.setEditable(false);

                //---- searchPathButton ----
                searchPathButton.setText("...");
                searchPathButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        searchPathButtonMouseClicked(e);
                    }
                });

                //---- titleLabel ----
                titleLabel.setText("Title");

                //---- label1 ----
                label1.setText("Steam AppID");

                //---- steamField ----
                steamField.setEditable(false);

                //---- label2 ----
                label2.setText("IGDB AppID");

                //---- igdbField ----
                igdbField.setEditable(false);

                //---- label3 ----
                label3.setText("Description");

                //---- label4 ----
                label4.setText("Release Date");

                //======== scrollPane1 ========
                {
                    scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                    //---- descriptionField ----
                    descriptionField.setLineWrap(true);
                    descriptionField.setWrapStyleWord(true);
                    scrollPane1.setViewportView(descriptionField);
                }

                //---- label5 ----
                label5.setText("Genre");

                //---- genreBox ----
                genreBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addComponent(pathField, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(searchPathButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(label1)
                                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(label5)
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addComponent(label2)
                                                        .addComponent(igdbField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4)
                                                        .addComponent(releaseField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(titleField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addComponent(steamField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(genreBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                            .addGap(69, 69, 69)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGap(86, 86, 86)
                                                    .addComponent(label3)
                                                    .addGap(104, 104, 104))
                                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(22, 22, 22)))))))
                            .addGap(14, 14, 14))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(pathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchPathButton))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(steamField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genreBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(14, 14, 14)
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(igdbField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(releaseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTextField pathField;
    private JButton searchPathButton;
    private JTextField titleField;
    private JLabel titleLabel;
    private JLabel label1;
    private JTextField steamField;
    private JLabel label2;
    private JTextField igdbField;
    private JLabel label3;
    private JTextField releaseField;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea descriptionField;
    private JLabel label5;
    private JComboBox genreBox;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
