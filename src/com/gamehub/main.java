package com.gamehub;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
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

        manager.loadAllImages();
       manager = com.gamehub.utils.JsonUtil.leerManager();
       //Cargamos los achievements despues
       manager.createAchievements();
       manager.verifyAchievements();



       if (manager.getUsers().isEmpty()) {
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


       }



        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getGameLaunches());


        //UI debug
        FlatOneDarkIJTheme.setup();




        // Crear el JFrame para la interfaz
        LoginGUI loginGUI = new LoginGUI(manager);

        // Configurar y mostrar el LoginGUI
        loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al salir
        loginGUI.setSize(670,470 ); // Tamaño del JFrame
        loginGUI.setLocationRelativeTo(null); // Centrar el JFrame en la pantalla
        loginGUI.setVisible(true); // Mostrar el JFrame


        JsonUtil.guardar("manager.json",loginGUI.getManager().toJson());
        System.out.println(loginGUI.getManager().toString());




        // Configurar el ícono del JFrame



    }
}


