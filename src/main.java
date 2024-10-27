import java.io.File;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        //Testing
        User user = new User("Joacolol");
        Manager manager = new Manager();
        GameNoSteam game_non = new GameNoSteam(new File("F:\\Games\\Others\\Dragon Ball Sparking ZERO\\SparkingZERO.exe"));
        GameSteam game_steam = new GameSteam(new File("E:\\SteamLibrary\\steamapps\\common\\Timberborn\\Timberborn.exe"));
        GameNoSteam game_non2 = new GameNoSteam("Parkasaurus",new File("F:\\SteamLibrary\\steamapps\\common\\Parkasaurus\\Parkasaurus.exe"));

        user.add(game_steam);
        user.add(game_non);
        user.add(game_non2);


        System.out.println(user.getGame(0).getTitle());
        System.out.println(user.getGame(1).getTitle());
        System.out.println(user.getGame(2).getTitle());
       // user.getGame(1).run();





    }
}
