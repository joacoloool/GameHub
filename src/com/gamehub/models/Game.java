package com.gamehub.models;

import com.gamehub.enums.Genre;
import com.gamehub.gui.LibraryGUI;
import com.gamehub.gui.ProfileGui;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.utils.IGDBHelper;
import com.gamehub.utils.ImageFormatter;
import com.gamehub.utils.SteamHelper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.gamehub.utils.ImageFormatter.*;


public class Game implements JsonConvertible {

    protected int id;
    protected String title;
    protected String description = "";
    protected boolean favorite = false;
    protected Genre genre = Genre.UNKNOWN;
    protected Timestamp lastTime;
    protected int gameLaunches = 0;
    protected File path;
    protected static int countID = 1;
    protected Icon icon; //DEBUG
    protected String appid = "";
    protected String appidIGDB = "";
    protected String url = "";
    protected String releaseDate = "";

    //Pictures

    protected Icon header;
    protected Icon image;
    protected String headerURL;
    protected String imageURL;

    //Constructors

    public Game(File path) {
        this.path = path;
        this.id = countID++;
        this.title = getPathTitle();
        this.appid = SteamHelper.getAppid(title);
        this.icon = extractIcon(); //DEBUG
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        this.appidIGDB = IGDBHelper.getAppid(title);
        if (!appid.isEmpty()) {
            generateSteamData();
        } else if (!appidIGDB.isEmpty()) {
            generateIGDBData();
        }
    }

    public Game(File path, String title) {
        this.path = path;
        this.id = countID++;
        this.title = title;
        this.appid = SteamHelper.getAppid(title);
        this.appidIGDB = IGDBHelper.getAppid(title);
        this.icon = extractIcon(); //DEBUG
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        if (!appid.isEmpty()) {
            generateSteamData();
        } else if (!appidIGDB.isEmpty()) {
            generateIGDBData();
        }
    }

    public Game() {
        if (lastTime == null) {
            this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        }

    }

    //Getters


    public Icon getHeader() {
        return header;
    }

    public void setHeader(Icon header) {
        this.header = header;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public String getAppidIGDB() {
        return appidIGDB;
    }

    public String getAppid() {
        return appid;
    }

    public String getTitle() {
        return title;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public String getLastTimeFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(lastTime);
    }

    public int getGameLaunches() {
        return gameLaunches;
    }

    public File getPath() {
        return path;
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

    public Icon getIcon() {
        return icon;
    }

    //Setters

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAppidIGDB(String appidIGDB) {
        this.appidIGDB = appidIGDB;
    }

    public static void setCountID(int countID) {
        Game.countID = countID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public void setGameLaunches(int gameLaunches) {
        this.gameLaunches = gameLaunches;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(File file) {
        this.icon = extractIcon();
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

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
            gameLaunches++;
            lastTime = Timestamp.valueOf(LocalDateTime.now());
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

    private void generateSteamData() {

        this.description = SteamHelper.getGameInfo(appid, "description");
        // this.genre = Genre.valueOf(com.gamehub.utils.SteamHelper.getGameInfo(appid,"genre"));
        this.title = SteamHelper.getGameInfo(appid, "name");
        this.url = generateSteamURL();
        this.releaseDate = SteamHelper.getGameInfo(appid, "release");
        //Pictures
        setImages();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(appid, game.appid) && Objects.equals(appidIGDB, game.appidIGDB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appid, appidIGDB);
    }

    private void generateIGDBData() {

        this.description = IGDBHelper.getGameInfo(appidIGDB, "description");
        this.title = IGDBHelper.getGameInfo(appidIGDB, "name");
        this.url = generateIGDBURL();
        this.releaseDate = IGDBHelper.getGameInfo("137989", "release");

        //Pictures
        setImages();
    }

    @Override
    public String toString() {
        return title;
    }

    public String getPlatform() {
        if (!appid.isEmpty()) {
            return "Steam";
        } else if (!appidIGDB.isEmpty()) {
            return "IGDB";

        }
        return "Unknown";
    }

    //DEBUG
    public Icon extractIcon() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        Icon icon = fsv.getSystemIcon(path);

        return upscaleIco(icon);
    }


    public void setImages() {
        if (!appid.isEmpty()) {
            this.headerURL = SteamHelper.getGameInfo(appid, "header");
            this.imageURL = SteamHelper.getGameInfo(appid, "image");
            this.header = urlToIcon(headerURL);
            this.image = urlToIcon(imageURL);
        } else if (!appidIGDB.isEmpty()) {
            headerURL = IGDBHelper.getGameInfo(appid, "header");
            imageURL = IGDBHelper.getGameInfo(appid, "image");
            this.header = urlToIcon(headerURL);
            this.image = urlToIcon(imageURL);
        }

        this.image= upscaleIco(image, LibraryGUI.imageLayered.getWidth(), LibraryGUI.imageLayered.getHeight());

    }

    public void saveImageGame(){
        String pathName = title + "_ig";
        ImageFormatter.saveProfileImageToFile(pathName,image);
    }

    public void saveHeader(){
        String pathName = title + "_he";
        ImageFormatter.saveProfileImageToFile(pathName,header);
    }

    public void loadImageGame() {
        String pathName = title + "_ig";
        System.out.println("Cargando imagen del juego: " + pathName); // Debug
        this.image = ImageFormatter.loadProfileImageFromFile(pathName);
    }

    public void loadHeaderGame() {
        String pathName = title + "_he";
        System.out.println("Cargando encabezado del juego: " + pathName); // Debug
        this.header = ImageFormatter.loadProfileImageFromFile(pathName);
    }

    private String generateSteamURL() {
        return "https://store.steampowered.com/app/" + appid;
    }

    private String generateIGDBURL() {
        return "https://www.igdb.com/games/" + title;
    }

    //Json

    public JSONObject toJson() {
        JSONObject game = new JSONObject();
        try {
            game.put("id", id);
            game.put("title", title);
            game.put("description", description);
            game.put("favorite", favorite);
            game.put("genre", genre);
            game.put("lastTime", lastTime.toString());
            game.put("gameLaunches", gameLaunches);
            game.put("path", path);
            game.put("countID", countID);
            game.put("icon", icon);
            game.put("appid", appid);
            game.put("appidIGDB", appidIGDB);
            game.put("url", url);
            game.put("releaseDate", releaseDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return game;
    }

}
