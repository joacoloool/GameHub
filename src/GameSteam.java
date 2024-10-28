import java.io.File;

public class GameSteam extends Game{

    protected String appid;


    public GameSteam(File path) {
        super(path);
        this.appid = SteamHelper.getAppid(title);

    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
