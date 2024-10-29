import E.Genre;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;



public class Game {

    protected int id = 0;
    protected String title;
    protected String description = "";
    protected boolean favorite = false;
    protected Genre genre;
    protected Timestamp lastTime;
    protected int playCount = 0;
    protected File path;
    protected static int countID = 0;
    protected Icon icon; //DEBUG
    protected String appid = "";
    protected String appidIGDB = "";
    protected String url = "";
    protected String releaseDate = "";

    //Pictures

    protected String header = "";
    protected String image = "";

    //Constructors

    public Game(File path) {
        this.path = path;
        this.id = countID++;
        this.title = getPathTitle();
        this.appid = SteamHelper.getAppid(title);
        this.icon = extractIcon(); //DEBUG
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        this.appidIGDB = IGDBHelper.getAppid(title);

        if (!appid.isEmpty()){
            generateSteamData();
        }
        else if (!appidIGDB.isEmpty()){
            generateIGDBData();
        }
    }

    public Game(File path,String title) {
        this.path = path;
        this.id = countID++;
        this.title = title;
        this.appid = SteamHelper.getAppid(title);
        this.appidIGDB = IGDBHelper.getAppid(title);

        this.icon = extractIcon(); //DEBUG
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());

        if (!appid.isEmpty()){
            generateSteamData();
        }
        else if (!appidIGDB.isEmpty()){
            generateIGDBData();
        }

    }

    //Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public static int getCountID() {
        return countID;
    }

    public int getId() {
        return id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setAppid(String appid){this.appid = appid;}

    //Methods

    public String getPathTitle() {

        String fileName = path.getName(); //Obtenemos el nombre del archivo
        if (fileName.contains(".")) { //Si el nombre contiene un punto entonces =
            int dotIndex = fileName.lastIndexOf('.'); //Guardamos el index de donde se encontró el punto
            return fileName.substring(0, dotIndex); //Retornamos lo que hay en el string antes del punto
        } else {
            return fileName; //Si el nombre no contiene un punto entonces lo devuelve normal.
        }
    }

    public void run() {
        try {
            Desktop.getDesktop().open(path);
            playCount++;
            lastTime =  Timestamp.valueOf(LocalDateTime.now());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "No se encontró la ubicación del archivo en el sistema.");
        }
    }

    public void openPath() {
        try {
            Desktop.getDesktop().open(path.getParentFile());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "No se encontró la ubicación del archivo en el sistema.");
        }
    }

    private void generateSteamData (){

            this.description = SteamHelper.getGameInfo(appid,"description");
           // this.genre = Genre.valueOf(SteamHelper.getGameInfo(appid,"genre"));
            this.title = SteamHelper.getGameInfo(appid,"name");
            this.url = generateSteamURL();
            this.releaseDate = SteamHelper.getGameInfo(appid,"release");

            //Pictures
            this.header = SteamHelper.getGameInfo(appid,"header");
            this.image = SteamHelper.getGameInfo(appid,"image");
    }

    private void generateIGDBData (){

        this.description = IGDBHelper.getGameInfo(appidIGDB,"description");
        this.title = IGDBHelper.getGameInfo(appidIGDB,"name");
        this.url = generateIGDBURL();
        this.releaseDate = IGDBHelper.getGameInfo("137989","release");

        //Pictures
        this.header = SteamHelper.getGameInfo(appid,"header");
        this.image = SteamHelper.getGameInfo(appid,"image");
    }
    //DEBUG
    private Icon extractIcon() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        Icon icon = fsv.getSystemIcon(path);

        return upscaleIcon(icon);
    }

    private Icon upscaleIcon(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg); // Devolver el ícono escalado
    }

    private String generateSteamURL(){
        return "https://store.steampowered.com/app/" + appid;
    }

    private String generateIGDBURL(){
        return "https://www.igdb.com/games/" + title;
    }



    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", favorite=" + favorite +
                ", genre=" + genre +
                ", lastTime=" + lastTime +
                ", playCount=" + playCount +
                ", path=" + path +
                ", icon=" + icon +
                ", appid='" + appid + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", header='" + header + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
