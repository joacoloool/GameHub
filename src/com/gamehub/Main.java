package com.gamehub;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.gamehub.gui.LoginGUI;
import com.gamehub.utils.JsonUtil;
import com.gamehub.managers.Manager;
import javax.swing.*;

/**
 * Clase principal que inicia la aplicación GameHub.
 * Esta clase se encarga de cargar la configuración, inicializar la interfaz de usuario
 * y gestionar la lógica de la aplicación al inicio.
 */
public class Main {
    public static void main(String[] args) {

        // Inicialización del gestor
        Manager manager = new Manager();

        // Cargar el gestor desde un archivo JSON si existe
        if (JsonUtil.leerManager() != null) {
            manager = JsonUtil.leerManager();
        }

        // Verificar y cargar los logros (achievements) si el gestor no es nulo
        if (manager != null) {
            manager.loadAllImages();
            manager.createAchievements();
            manager.verifyAchievements();
        }

        // Configuración del tema de la interfaz de usuario
        FlatOneDarkIJTheme.setup();

        // Crear el JFrame para la interfaz de usuario
        LoginGUI loginGUI = new LoginGUI(manager);

        // Configurar y mostrar el LoginGUI
        loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al salir
        loginGUI.setSize(670, 470); // Tamaño del JFrame
        loginGUI.setLocationRelativeTo(null); // Centrar el JFrame en la pantalla
        loginGUI.setVisible(true); // Mostrar el JFrame

        // Guardar el estado del gestor en un archivo JSON
        JsonUtil.guardar("manager.json", loginGUI.getManager().toJson());
        System.out.println(loginGUI.getManager().toString());
    }
}


//gracias por ver :)
//en honor a alan arruti dep