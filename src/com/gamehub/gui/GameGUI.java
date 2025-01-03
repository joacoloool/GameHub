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
 * Clase que representa la interfaz gráfica para gestionar la información de un juego.
 * Permite visualizar y editar los detalles de un juego seleccionado.
 */
public class GameGUI extends JDialog {

    private final LibraryGUI libraryGUI; // Referencia a la interfaz de la biblioteca de juegos.

    /**
     * Constructor que inicializa la interfaz con un juego seleccionado.
     *
     * @param selectedGame El juego que se va a mostrar.
     * @param libraryGUI   La interfaz de la biblioteca de juegos.
     */
    public GameGUI(Game selectedGame, LibraryGUI libraryGUI) {
        initComponents(); // Inicializa los componentes de la interfaz.
        this.libraryGUI = libraryGUI; // Guarda la referencia a la biblioteca.
        setLabels(selectedGame); // Establece los campos con la información del juego seleccionado.
    }

    /**
     * Constructor que inicializa la interfaz sin un juego seleccionado.
     *
     * @param libraryGUI La interfaz de la biblioteca de juegos.
     */
    public GameGUI(LibraryGUI libraryGUI) {
        initComponents(); // Inicializa los componentes de la interfaz.
        this.libraryGUI = libraryGUI; // Guarda la referencia a la biblioteca.
    }

    /**
     * Metodo que se ejecuta al hacer clic en el botón de cancelar.
     * Cierra la ventana del diálogo.
     */
    private void cancelButtonMouseClicked(MouseEvent e) {
        closeWindow(); // Cierra la ventana si se hace clic en el botón de cancelar.
    }

    /**
     * Metodo que cierra la ventana del diálogo.
     */
    private void closeWindow() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); // Cierra la ventana del diálogo.
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el botón para buscar un archivo.
     * Abre un cuadro de diálogo para seleccionar un archivo y establece los campos con la información del archivo seleccionado.
     */
    private void searchPathButtonMouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser(); // Crea un cuadro de diálogo para seleccionar un archivo.
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Configura para seleccionar solo archivos.
        int result = chooser.showOpenDialog(null); // Muestra el cuadro de diálogo.

        // Si se selecciona un archivo, actualiza los campos con la información del archivo.
        if (result == JFileChooser.APPROVE_OPTION) {
            setLabels(chooser.getSelectedFile());
        }
    }

    /**
     * Metodo que establece los campos de texto con la información del juego.
     *
     * @param game El juego del cual se obtendrá la información.
     */
    private void setLabels(Game game) {
        // Establece los campos de texto con la información del juego.
        titleField.setText(game.getTitle());
        releaseField.setText(game.getReleaseDate());
        descriptionField.setText(game.getDescription());
        igdbField.setText(game.getAppidIGDB());
        steamField.setText(game.getAppid());
        pathField.setText(game.getPath().getAbsolutePath());
        genreBox.setSelectedItem(game.getGenre());
    }

    /**
     * Metodo que establece los campos de texto a partir de un archivo seleccionado.
     *
     * @param file El archivo seleccionado que representa un juego.
     */
    private void setLabels(File file) {
        // Establece la ruta del archivo y crea un nuevo objeto Game a partir del archivo.
        pathField.setText(file.getAbsolutePath());
        Game game = new Game(file); // Crea un nuevo objeto Game a partir del archivo.
        // Establece los campos de texto con la información del juego.
        descriptionField.setText(game.getDescription());
        titleField.setText(game.getTitle());
        releaseField.setText(game.getReleaseDate());
        igdbField.setText(game.getAppidIGDB());
        steamField.setText(game.getAppid());
        genreBox.setSelectedItem(game.getGenre());
    }

    /**
     * Metodo que se ejecuta al hacer clic en el botón OK.
     * Crea un nuevo objeto Game con la información ingresada en los campos de texto.
     */
    public void okButtonMouseClicked(MouseEvent e) {
        // Crea un nuevo objeto Game y establece sus propiedades a partir de los campos de texto.
        File file = new File(pathField.getText());
        Game game = new Game();
        game.setTitle(titleField.getText());
        game.setReleaseDate(releaseField.getText());
        game.setAppidIGDB(igdbField.getText());
        game.setDescription(descriptionField.getText());
        game.setAppid(steamField.getText());
        game.setPath(new File(pathField.getText()));
        game.setGenre((Genre) genreBox.getSelectedItem());
        game.setIcon(game.extractIcon()); // Extrae el ícono del juego.
        game.setImages(); // Establece las imágenes del juego.
        closeWindow(); // Cierra la ventana después de guardar la información.

        // Verifica si el juego agregado es el mismo que el juego seleccionado en la biblioteca.
        if (game.equals(libraryGUI.selectedGame)) {
            libraryGUI.user.deleteGame(game); // Elimina el juego de la biblioteca si es el mismo.
            libraryGUI.gamesListModel.remove(libraryGUI.gamesListModel.indexOf(game)); // Elimina el juego de la lista.
        }
        libraryGUI.addGame(game); // Agrega el nuevo juego a la biblioteca.
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
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
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
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
    // Generated using JFormDesigner Evaluation license - Joaquin Albornoz
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
