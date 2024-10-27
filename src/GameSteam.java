import java.io.File;

public class GameSteam extends Game{

    protected int appid;


    public GameSteam(File path) {
        super(path);
        this.appid = SteamHelper.getAppid(path.getParentFile());
    }
}
