/*
 * Created by JFormDesigner on Wed Nov 13 17:52:52 ART 2024
 */
package com.gamehub.gui;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.event.*;

import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.exceptions.NonExistObjectException;
import com.gamehub.gui.utilities.GameCellRender;
import com.gamehub.models.Game;
import com.gamehub.models.User;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;


/**
 * @author Administrator
 */
public class LibraryGUI extends JPanel {

    DefaultListModel<Game> gamesListModel = new DefaultListModel<>();
    User user;
    Game selectedGame;

    public LibraryGUI(User user) {
        initComponents();
        gamesList.setModel(gamesListModel);
        gamesList.setCellRenderer(new GameCellRender());
        this.user = user;


        for (Game game : user.getGameList()) {
            gamesListModel.addElement(game);
        }
    }



    public void addGame(Game game) {//añadi extra seguridad cosa de que solo exista un juego
        try {
            user.addGame(game);
            gamesListModel.addElement(game);
        } catch (DuplicateElementException e) {
            JOptionPane.showMessageDialog(null, "Este juego ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addGameMouseClicked(MouseEvent e) {
        GameGUI addgame = new GameGUI(this);  // 'this' es la referencia de LibraryGUI
        addgame.setVisible(true);
    }

    private void updateInfo() {

        if (selectedGame != null) {
            if (selectedGame.getLastTime() != null) {
                lastPlayedL.setText(selectedGame.getLastTimeFormatted());
            } else {
                lastPlayedL.setText("Never played.");
            }
            if (selectedGame.getGenre() != null) {
                genreL.setText(String.valueOf(selectedGame.getGenre()));
            } else {
                genreL.setText("Unknown.");
            }
            platformL.setText(selectedGame.getPlatform());
            if (selectedGame.getReleaseDate() != null) {
                releaseL.setText(String.valueOf(selectedGame.getReleaseDate()));
            }
            if (selectedGame.getDescription() != null) {
                descriptionL.setText(selectedGame.getDescription());
            } else {
                descriptionL.setText("This game not have any description.");
            }
        }
    }

    private void playButtonMouseClicked(MouseEvent e) {
        try {
            selectedGame.run();
            updateInfo();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "You have not selected a game");
        }
    }

    private void selectedGame(ListSelectionEvent e) {
        selectedGame = (Game) gamesList.getSelectedValue();
        updateInfo();
        updatePics();
    }

    private void updatePics() {
        if (selectedGame != null) {
            imageL.setIcon(selectedGame.getImage());
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void gamesListRightClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            selectedGame = (Game) gamesList.getSelectedValue();
            if (selectedGame != null) {
                popupList.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }


    private void contextPlayMouseClicked(MouseEvent e) {
        playButtonMouseClicked(e);
        popupList.setVisible(false);
    }

    private void contextDeleteMouseClicked(MouseEvent e) {
        Game game = selectedGame;
        try {
            if (selectedGame != null) {
                int index = gamesListModel.indexOf(selectedGame);
                if (index != -1) {
                    gamesListModel.remove(index);
                    user.deleteGame(game);
                }
            }
            popupList.setVisible(false);
        } catch (NonExistObjectException e1) {
            // Mostrar un mensaje de error en un pop-up
            JOptionPane.showMessageDialog(null, "Este juego no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void contextModifyMouseClicked(MouseEvent e) {
        GameGUI modifyGame = new GameGUI(selectedGame, this);  // 'this' es la referencia de LibraryGUI
        modifyGame.setAlwaysOnTop(true);
        modifyGame.setVisible(true);
    }

    private void contextFavoriteMouseClicked(MouseEvent e) {
        selectedGame.setFavorite(!selectedGame.getFavorite());
        orderByFavorite();
    }

    private void orderByFavorite() {
        ArrayList<Game> games = new ArrayList<>();
        ArrayList<Game> others = new ArrayList<>();

        for (int i = 0; i < gamesListModel.getSize(); i++) {
            if (gamesListModel.getElementAt(i).getFavorite()) {
                games.add(gamesListModel.getElementAt(i));
            } else {
                others.add(gamesListModel.getElementAt(i));
            }
        }
        gamesListModel.removeAllElements();
        for (Game game : games) {
            gamesListModel.addElement(game);
        }
        for (Game other : others) {
            gamesListModel.addElement(other);
        }
    }

    private void addGamePlusMouseEntered(MouseEvent e) {
        addGame.setForeground(Color.white);
        addGamePlus.setForeground(Color.white);
    }

    private void addGameMouseExited(MouseEvent e) {
        addGame.setForeground(Color.decode("#999999"));
        addGamePlus.setForeground(Color.decode("#999999"));
    }

    private void openPathButtonMouseClicked(MouseEvent e) {
        selectedGame.openPath();
    }

    private void sortButtonMouseClicked(MouseEvent e) {
        sortMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void nameButtonMouseClicked(MouseEvent e) {
        user.sortName();
        gamesListModel.removeAllElements();
        gamesListModel.addAll(user.getGameList());
        this.repaint();
    }

    private void lastButtonMouseClicked(MouseEvent e) {
        user.sortLastTime();
        gamesListModel.removeAllElements();
        gamesListModel.addAll(user.getGameList());
        this.repaint();
    }

    private void mostButtonMouseClicked(MouseEvent e) {
        user.sortPlayCount();
        gamesListModel.removeAllElements();
        gamesListModel.addAll(user.getGameList());
        this.repaint();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
        scrolPanelGames = new JScrollPane();
        gamesList = new JList();
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
        scrollPane1 = new JScrollPane();
        descriptionL = new JTextArea();
        imageLayered = new JLayeredPane();
        imageText = new JLabel();
        shadow = new JLabel();
        imageL = new JLabel();
        layeredPane1 = new JLayeredPane();
        addGamePlus = new JLabel();
        addGame = new JButton();
        sortButton = new JButton();
        popupList = new JPopupMenu();
        contextPlay = new JButton();
        contextFavorite = new JButton();
        contextModify = new JButton();
        contextDelete = new JButton();
        openPathButton = new JButton();
        sortMenu = new JPopupMenu();
        nameButton = new JButton();
        lastButton = new JButton();
        mostButton = new JButton();

        //======== this ========
        setBorder(new LineBorder(new Color(0x22000000, true), 3));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er"
        .equals(e.getPropertyName()))throw new RuntimeException();}});

        //======== scrolPanelGames ========
        {
            scrolPanelGames.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- gamesList ----
            gamesList.setFocusable(false);
            gamesList.addListSelectionListener(e -> selectedGame(e));
            gamesList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    gamesListRightClick(e);
                }
            });
            scrolPanelGames.setViewportView(gamesList);
        }

        //======== panel1 ========
        {
            panel1.setBorder(null);
            panel1.setBackground(new Color(0x002a2c2f, true));

            //---- playButton ----
            playButton.setText("PLAY");
            playButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
            playButton.setBackground(new Color(0x44c335));
            playButton.setForeground(Color.white);
            playButton.setBorder(null);
            playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            lastText.setOpaque(true);

            //---- lastPlayedL ----
            lastPlayedL.setText("30/05/oct");
            lastPlayedL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            lastPlayedL.setForeground(Color.gray);
            lastPlayedL.setHorizontalAlignment(SwingConstants.CENTER);
            lastPlayedL.setOpaque(true);
            lastPlayedL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

            //---- genreText ----
            genreText.setText("Genre");
            genreText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            genreText.setForeground(new Color(0xbabbb4));
            genreText.setOpaque(true);

            //---- genreL ----
            genreL.setText("Action");
            genreL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            genreL.setForeground(Color.gray);
            genreL.setHorizontalAlignment(SwingConstants.CENTER);
            genreL.setOpaque(true);
            genreL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

            //---- platformL ----
            platformL.setText("Steam");
            platformL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            platformL.setForeground(Color.gray);
            platformL.setHorizontalAlignment(SwingConstants.CENTER);
            platformL.setOpaque(true);
            platformL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

            //---- PlatformText ----
            PlatformText.setText("Platform");
            PlatformText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            PlatformText.setForeground(new Color(0xbabbb4));
            PlatformText.setOpaque(true);

            //---- releaseText ----
            releaseText.setText("Release Date");
            releaseText.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 17));
            releaseText.setForeground(new Color(0xbabbb4));
            releaseText.setOpaque(true);

            //---- releaseL ----
            releaseL.setText("Steam");
            releaseL.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 13));
            releaseL.setForeground(Color.gray);
            releaseL.setHorizontalAlignment(SwingConstants.CENTER);
            releaseL.setOpaque(true);
            releaseL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

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

            //======== scrollPane1 ========
            {

                //---- descriptionL ----
                descriptionL.setEditable(false);
                descriptionL.setLineWrap(true);
                descriptionL.setWrapStyleWord(true);
                descriptionL.setFocusable(false);
                descriptionL.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                scrollPane1.setViewportView(descriptionL);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(scrollPane1)
                        .addGap(97, 97, 97))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );
        }

        //======== imageLayered ========
        {

            //---- imageText ----
            imageText.setFont(new Font("Inconsolata", Font.BOLD, 50));
            imageLayered.add(imageText, JLayeredPane.DEFAULT_LAYER);
            imageText.setBounds(25, 225, 750, 62);

            //---- shadow ----
            shadow.setText(".c");
            shadow.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Facultad\\Github\\GameHub10\\src\\com\\gamehub\\images\\headers\\shadow.png"));
            imageLayered.add(shadow, JLayeredPane.DEFAULT_LAYER);
            shadow.setBounds(0, -5, 1160, 660);

            //---- imageL ----
            imageL.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/headers/headerProfile.png")));
            imageL.setVerticalAlignment(SwingConstants.BOTTOM);
            imageLayered.add(imageL, JLayeredPane.DEFAULT_LAYER);
            imageL.setBounds(0, 0, 800, 305);
        }

        //======== layeredPane1 ========
        {

            //---- addGamePlus ----
            addGamePlus.setText("+");
            addGamePlus.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 23));
            addGamePlus.setForeground(new Color(0x999999));
            addGamePlus.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addGamePlusMouseEntered(e);
                }
            });
            layeredPane1.add(addGamePlus, JLayeredPane.DEFAULT_LAYER);
            addGamePlus.setBounds(0, 5, 15, 20);

            //---- addGame ----
            addGame.setText("       Add a Game");
            addGame.setBorder(null);
            addGame.setFont(addGame.getFont().deriveFont(addGame.getFont().getSize() - 1f));
            addGame.setBorderPainted(false);
            addGame.setContentAreaFilled(false);
            addGame.setForeground(new Color(0x999999));
            addGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addGame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addGameMouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    addGamePlusMouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    addGameMouseExited(e);
                }
            });
            layeredPane1.add(addGame, JLayeredPane.DEFAULT_LAYER);
            addGame.setBounds(-10, 0, 100, 28);
        }

        //---- sortButton ----
        sortButton.setFocusable(false);
        sortButton.setIcon(UIManager.getIcon("InternalFrame.minimizeIcon"));
        sortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sortButtonMouseClicked(e);
                sortButtonMouseClicked(e);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrolPanelGames, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sortButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(imageLayered)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(imageLayered, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sortButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrolPanelGames)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
        );

        //======== popupList ========
        {

            //---- contextPlay ----
            contextPlay.setText("Play");
            contextPlay.setBackground(new Color(0x44c335));
            contextPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            contextPlay.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contextPlayMouseClicked(e);
                }
            });
            popupList.add(contextPlay);

            //---- contextFavorite ----
            contextFavorite.setText("Favorite");
            contextFavorite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            contextFavorite.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contextFavoriteMouseClicked(e);
                }
            });
            popupList.add(contextFavorite);

            //---- contextModify ----
            contextModify.setText("Modify");
            contextModify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            contextModify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contextModifyMouseClicked(e);
                }
            });
            popupList.add(contextModify);

            //---- contextDelete ----
            contextDelete.setText("Delete");
            contextDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            contextDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contextDeleteMouseClicked(e);
                }
            });
            popupList.add(contextDelete);

            //---- openPathButton ----
            openPathButton.setText("Path");
            openPathButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openPathButtonMouseClicked(e);
                }
            });
            popupList.add(openPathButton);
        }

        //======== sortMenu ========
        {

            //---- nameButton ----
            nameButton.setText("Name");
            nameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nameButtonMouseClicked(e);
                }
            });
            sortMenu.add(nameButton);

            //---- lastButton ----
            lastButton.setText("LastPlay");
            lastButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lastButtonMouseClicked(e);
                }
            });
            sortMenu.add(lastButton);

            //---- mostButton ----
            mostButton.setText("MorePlay");
            mostButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mostButtonMouseClicked(e);
                }
            });
            sortMenu.add(mostButton);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - VALERIA MARQUEZ
    private JScrollPane scrolPanelGames;
    private JList gamesList;
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
    private JScrollPane scrollPane1;
    private JTextArea descriptionL;
    public static JLayeredPane imageLayered;
    private JLabel imageText;
    private JLabel shadow;
    private JLabel imageL;
    private JLayeredPane layeredPane1;
    private JLabel addGamePlus;
    private JButton addGame;
    private JButton sortButton;
    private JPopupMenu popupList;
    private JButton contextPlay;
    private JButton contextFavorite;
    private JButton contextModify;
    private JButton contextDelete;
    private JButton openPathButton;
    private JPopupMenu sortMenu;
    private JButton nameButton;
    private JButton lastButton;
    private JButton mostButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
