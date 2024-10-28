import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        //Testing
        User user = new User("Joacolol");
        Manager manager = new Manager();
        GameNoSteam game_non = new GameNoSteam(new File("F:\\Games\\Others\\Dragon Ball Sparking ZERO\\SparkingZERO.exe"));
        GameSteam game_steam = new GameSteam(new File("E:\\SteamLibrary\\steamapps\\common\\Timberborn\\Timberborn.exe"));
        GameNoSteam game_non2 = new GameNoSteam("Parkasaurus", new File("F:\\SteamLibrary\\steamapps\\common\\Parkasaurus\\Parkasaurus.exe"));

        user.add(game_steam);
        user.add(game_non);
        user.add(game_non2);


        System.out.println(user.getGame(0).getTitle());
        System.out.println(user.getGame(1).getTitle());
        System.out.println(user.getGame(2).getTitle());
        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getPlayCount());



        //Testeos de Sort

        user.sortName();
        user.sortLastTime();
        user.getGame(0).setFavorite(true);
        user.sortFavorite();
        user.getGame(1).setPlayCount(4);
        user.getGame(0).setPlayCount(2);
        user.getGame(2).setPlayCount(10);
        user.sortPlayCount();


        for (Game g : user.getGames()) {
            System.out.println(g.getTitle());
            System.out.println(g.getPlayCount());
            System.out.println(g.getLastTime());
            System.out.println(g.getFavorite());
        }




        //Testeos de SteamHelper
        if (user.getGame(0) instanceof GameSteam) {
            System.out.println(((GameSteam) user.getGame(0)).getAppid());
            System.out.println(SteamHelper.getGameInfo("1062090", "description"));
            System.out.println(SteamHelper.getGameInfo("1062090", "image"));
            System.out.println(SteamHelper.getGameInfo("1062090", "header"));
            System.out.println(SteamHelper.getGameInfo("1062090", "release"));
            System.out.println(SteamHelper.getGameInfo("1062090", "genre"));


            System.out.println(((GameSteam) user.getGame(0)).getUrl());



        }

/*
        //IMAGE DEBUG BANNER

        // Crear un marco
        JFrame frame = new JFrame("Imagen desde URL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.setLayout(new BorderLayout());

        // URL de la imagen (Cambia segun appid

        //Assassins creed 2
        //String imageUrl = SteamHelper.getGameInfo("33230","image");
        //GTA V
        String imageUrl = SteamHelper.getGameInfo("218620","header");
        //COUNTER STRIKE 2
        //String imageUrl = SteamHelper.getGameInfo("730","image");

        try {
            // Cargar la imagen desde la URL
            URL url = new URL(imageUrl);
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));

            // Crear un JLabel para mostrar la imagen
            JLabel label = new JLabel(imageIcon);
            frame.add(label, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Hacer visible el marco
        frame.setVisible(true);



*/
    }
}

