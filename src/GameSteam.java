import java.io.File;


public class GameSteam extends Game{

    protected String appid;
    protected String url;


    public GameSteam(File path) {
        super(path);
        this.appid = SteamHelper.getAppid(title);
        this.url = "https://store.steampowered.com/app/" + appid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
