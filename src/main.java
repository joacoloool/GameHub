import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        //Testing
        //Manager manager = new Manager();
        Manager manager = JsonUtil.leerManager();

        //System.out.println(game_non2.toString());

        User user = new User("Joacolool");
        User user2 = new User("Tom");
        manager.addUser(user);
        manager.addUser(user2);


        manager.verifyAchievements();



        ArrayList<Game> games = new ArrayList<>();



       // System.out.println(games.toString());

        manager.getUsers().getFirst().feed.createPost("HelloWorld");
        manager.getUsers().getFirst().feed.likePost(0);
        manager.getUsers().getFirst().feed.likePost(0);
        manager.getUsers().getFirst().feed.likePost(0);
        manager.getUsers().getFirst().feed.likePost(0);
        System.out.println(manager.getUsers().getFirst().feed.posts.toString());


      JsonUtil.sobreescribir("manager.json",JsonUtil.ManagerToJSONObject(manager));

        for (int i=0;i<manager.users.get(0).gameList.size();i++) {
            System.out.println(manager.users.get(0).gameList.get(i).getId());
            System.out.println(manager.users.get(0).gameList.get(i).toString()+"\n");
        }
        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getPlayCount());



        //Testeos de Sort


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

