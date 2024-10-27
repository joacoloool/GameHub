import java.io.File;

public class GameNoSteam extends Game {

    //Constructors

    public GameNoSteam(File path) {
        super(path);
    }

    public GameNoSteam(String title, File path) {
        super(title, path);
    }
}
