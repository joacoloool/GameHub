package com.gamehub;
import com.gamehub.gui.*;
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
           User user2 = new User("Tom");
           manager.addUser(user);
           manager.addUser(user2);
           manager.getUsers().getFirst().getFeed().createPost("1HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("12HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("123HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("1234HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("12345HelloWorld");
           manager.getUsers().getFirst().getFeed().togglePinPost(manager.getUsers().getFirst().getFeed().getPosts().get(4));
           manager.getUsers().getFirst().getFeed().togglePinPost(manager.getUsers().getFirst().getFeed().getPosts().get(0));


       }

      JsonUtil.guardar("manager.json", manager.toJson());

        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getGameLaunches());


        //UI debug

        FlatGitHubDarkIJTheme.setup();

        // Crear el JFrame para la interfaz
        JFrame frame = new JFrame("GameHub");

        // Crear la instancia de MainGUI
        MainGUI mainGui = new MainGUI();

        // Configurar el JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que cierre la aplicación al cerrar la ventana
        frame.setSize(1024, 768); // Establecer tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.add(mainGui); // Agregar el panel principal (MainGUI) al JFrame
        frame.setVisible(true); // Hacer visible la ventana

        
    }
}


