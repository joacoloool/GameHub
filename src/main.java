import java.io.File;

public class main {
    public static void main(String[] args) {

        //Testing
        Manager manager = new Manager();

        //System.out.println(game_non2.toString());

        Game game = new Game(new File("C:\\Users\\joaal\\OneDrive\\Escritorio\\Party Animals.url"));


        User user = new User("Joacolool");
        manager.addUser(user);
        manager.users.get(0).gameList().add(game);
        manager.users.get(0).setOpenGameCounter(1);
        manager.verifyAchievements();





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

