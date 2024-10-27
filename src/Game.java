import java.io.File;
import java.sql.Time;
import java.time.LocalTime;


public abstract class Game {
    protected int id;
    protected String title;
    protected String description;
    protected Boolean favorite;
    protected Genre genre;
    protected Time lastTime;
    protected int playCount = 0;
    protected File path;
    protected static int countID = 0;

    public Game(int id, String title, String description, Genre genre, Boolean favorite, Time lastTime, File path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.favorite = favorite;
        this.lastTime = lastTime;
        this.path = path;
    }

    public Game(){
        this.id = countID++;
    }

    public Game(String title, File path){
        this.id = countID++;
        this.title = title;
        this.path = path;
    }

    public void run(){

        //Implement
        playCount++;
        lastTime = Time.valueOf(LocalTime.now());
    }

    public void openPath(){
        //Implement
    }




}
