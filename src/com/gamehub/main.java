package com.gamehub;
import com.gamehub.enums.AchievType;
import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.gui.*;
import com.gamehub.models.Achievement;
import com.gamehub.models.Game;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;
import com.gamehub.managers.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import scala.App;

import javax.swing.*;


public class main {
    public static void main(String[] args) {

        //Testing

        Manager manager = new Manager();
       //com.gamehub.managers.Manager manager = com.gamehub.utils.JsonUtil.leerManager();

       if (manager.getUsers().isEmpty()) {
           User user = new User("Joacolool");
           User user2 = new User("Joacolool");

          Game game= new Game();

          
          /*
          try {
              user.addGame(game);
          }
          catch (DuplicateElementException e)
          {
              System.err.println(e.getMessage());
          }

           try {
               user.addGame(game);
           }
           catch (DuplicateElementException e)
           {
               System.err.println(e.getMessage());
           }
        */
           manager.addUser(user);
           manager.addUser(user2);
           System.out.println(manager.getUsers());

       }



        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getGameLaunches());


        //UI debug
        FlatGitHubDarkIJTheme.setup();


        // Crear el JFrame para la interfaz
        JFrame frame = new JFrame("GameHub");

        // Crear la instancia de MainGUI
        MainGUI mainGui = new MainGUI(manager);

        // Configurar el JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que cierre la aplicación al cerrar la ventana
        frame.setSize(1024, 768); // Establecer tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.add(mainGui); // Agregar el panel principal (MainGUI) al JFrame
        frame.setVisible(true); // Hacer visible la ventana
        ImageIcon imageIcon = new ImageIcon("gamhub2.png");
        frame.setIconImage(imageIcon.getImage());
        System.out.println(manager.getUsers().getFirst().getMyAchievements());


    }
}


