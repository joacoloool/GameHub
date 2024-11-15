/*
 * Created by JFormDesigner on Wed Nov 13 17:52:52 ART 2024
 */

package com.gamehub.gui;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.gamehub.gui.utilities.GameCellRender;
import com.gamehub.models.Game;
import com.gamehub.models.User;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Administrator
 */
public class LibraryGUI extends JPanel {

    DefaultListModel<Game> gamesListModel = new DefaultListModel<>();
    Game selectedGame = new Game();

    public LibraryGUI(User user) {
        initComponents();
        gamesList.setModel(gamesListModel);
        gamesList.setCellRenderer(new GameCellRender());

        for (Game game : user.getGameList()) {
            gamesListModel.addElement(game);
        }
    }

    public void addGame(Game game) {
        gamesListModel.addElement(game);
    }

    private void addGameMouseClicked(MouseEvent e) {
        GameGUI addgame = new GameGUI(this);  // 'this' es la referencia de LibraryGUI
        addgame.setVisible(true);
    }

    private void updateInfo(){
        if(selectedGame.getLastTime() != null){
            lastPlayedL.setText(selectedGame.getLastTimeFormatted());
        }
        else {
            lastPlayedL.setText("Never played.");
        }
        if (selectedGame.getGenre() != null) {
            genreL.setText(String.valueOf(selectedGame.getGenre()));
        }
        else {
            genreL.setText("Unknown.");
        }
        platformL.setText(selectedGame.getPlatform());
        if (selectedGame.getReleaseDate() != null) {
            releaseL.setText(String.valueOf(selectedGame.getReleaseDate()));
        }
        if (selectedGame.getDescription() != null) {
            descriptionL.setText(selectedGame.getDescription());
        }
        else{
            descriptionL.setText("This game not have any description.");
        }

    }

    private void playButtonMouseClicked(MouseEvent e) {
        try{
            selectedGame.run();
            updateInfo();
        }
        catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "You have not selected a game");
        }

    }

    private void selectedGame(ListSelectionEvent e) {
        selectedGame = (Game) gamesList.getSelectedValue();
        updateInfo();
        updatePics();

    }

    private void updatePics() {
        headerL.setIcon(selectedGame.getHeader());
        imageL.setIcon(selectedGame.getImage());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
        scrolPanelGames = new JScrollPane();
        gamesList = new JList();
        addGame = new JButton();
        imageL = new JLabel();
        panel1 = new JPanel();
        playButton = new JButton();
        lastText = new JLabel();
        lastPlayedL = new JLabel();
        genreText = new JLabel();
        genreL = new JLabel();
        platformL = new JLabel();
        PlatformText = new JLabel();
        releaseText = new JLabel();
        releaseL = new JLabel();
        panel2 = new JPanel();
        headerL = new JLabel();
        scrollPane1 = new JScrollPane();
        descriptionL = new JTextArea();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
        12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //======== scrolPanelGames ========
        {
            scrolPanelGames.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- gamesList ----
            gamesList.setFocusable(false);
            gamesList.addListSelectionListener(e -> selectedGame(e));
            scrolPanelGames.setViewportView(gamesList);
        }

        //---- addGame ----
        addGame.setText("Add Game +");
        addGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addGameMouseClicked(e);
            }
        });

        //---- imageL ----
        imageL.setIcon(new ImageIcon("D:\\Aprendiendo_Java\\GameHub\\Proyecto nuevo (3).png"));
        imageL.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        imageL.setVerticalAlignment(SwingConstants.BOTTOM);
        imageL.setBorder(new BevelBorder(BevelBorder.LOWERED));
        imageL.setVerticalTextPosition(SwingConstants.BOTTOM);
        imageL.setFont(new Font("Inter", Font.ITALIC, 50));
        imageL.setRequestFocusEnabled(false);

        //======== panel1 ========
        {
            panel1.setBorder(null);

            //---- playButton ----
            playButton.setText("PLAY");
            playButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
            playButton.setBackground(new Color(0x44c335));
            playButton.setForeground(Color.white);
            playButton.setBorder(null);
            playButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playButtonMouseClicked(e);
                }
            });

            //---- lastText ----
            lastText.setText("Last Played");
            lastText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            lastText.setForeground(new Color(0xbabbb4));

            //---- lastPlayedL ----
            lastPlayedL.setText("30/05/oct");
            lastPlayedL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            lastPlayedL.setForeground(Color.gray);
            lastPlayedL.setHorizontalAlignment(SwingConstants.CENTER);

            //---- genreText ----
            genreText.setText("Genre");
            genreText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            genreText.setForeground(new Color(0xbabbb4));

            //---- genreL ----
            genreL.setText("Action");
            genreL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            genreL.setForeground(Color.gray);
            genreL.setHorizontalAlignment(SwingConstants.CENTER);

            //---- platformL ----
            platformL.setText("Steam");
            platformL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            platformL.setForeground(Color.gray);
            platformL.setHorizontalAlignment(SwingConstants.CENTER);

            //---- PlatformText ----
            PlatformText.setText("Platform");
            PlatformText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            PlatformText.setForeground(new Color(0xbabbb4));

            //---- releaseText ----
            releaseText.setText("Release Date");
            releaseText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            releaseText.setForeground(new Color(0xbabbb4));

            //---- releaseL ----
            releaseL.setText("Steam");
            releaseL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            releaseL.setForeground(Color.gray);
            releaseL.setHorizontalAlignment(SwingConstants.CENTER);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lastPlayedL, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(genreL, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lastText, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(genreText, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(PlatformText, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                            .addComponent(platformL, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(releaseL, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addComponent(releaseText, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(lastText)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lastPlayedL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(genreL, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(platformL, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(releaseL, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(playButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(genreText)
                                        .addComponent(PlatformText)
                                        .addComponent(releaseText)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new EtchedBorder());

            //---- headerL ----
            headerL.setText("Header");
            headerL.setBorder(null);

            //======== scrollPane1 ========
            {

                //---- descriptionL ----
                descriptionL.setEditable(false);
                descriptionL.setLineWrap(true);
                descriptionL.setWrapStyleWord(true);
                descriptionL.setFocusable(false);
                scrollPane1.setViewportView(descriptionL);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerL, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 79, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(scrollPane1)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(headerL, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(156, Short.MAX_VALUE))
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addGame, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(scrolPanelGames, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(imageL, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                    .addGap(12, 12, 12))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(12, 12, 12))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrolPanelGames, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(imageL, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(addGame)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
    private JScrollPane scrolPanelGames;
    private JList gamesList;
    private JButton addGame;
    private JLabel imageL;
    private JPanel panel1;
    private JButton playButton;
    private JLabel lastText;
    private JLabel lastPlayedL;
    private JLabel genreText;
    private JLabel genreL;
    private JLabel platformL;
    private JLabel PlatformText;
    private JLabel releaseText;
    private JLabel releaseL;
    private JPanel panel2;
    private JLabel headerL;
    private JScrollPane scrollPane1;
    private JTextArea descriptionL;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
