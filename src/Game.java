import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;


public abstract class Game {
    protected int id;
    protected String title;
    protected String description = "";
    protected boolean favorite = false;
    protected Genre genre;
    protected Time lastTime;
    protected int playCount = 0;
    protected File path;
    protected static int countID = 0;

    //Constructors

    public Game(File path) {
        this.id = countID++;
        this.path = path;
        this.title = getPathTitle();
        this.description = SteamHelper.getGameinfo(title, "Description");
    }

    public Game(String title,File path) {
        this.id = countID++;
        this.path = path;
        this.title = title;
        this.description = SteamHelper.getGameinfo(title, "Description");
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

    public Time getLastTime() {
        return lastTime;
    }

    public void setLastTime(Time lastTime) {
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
            lastTime = Time.valueOf(LocalTime.now());
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


}
