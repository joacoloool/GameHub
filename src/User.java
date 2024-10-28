import I.SortTool;

import java.util.ArrayList;
import java.util.Comparator;

public class User implements SortTool<Game> {
    protected int id;
    protected ArrayList<Game> gameList;
    protected String name;

    public User(String name) {
        this.name = name;
        this.gameList = new ArrayList<>();
    }


    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Game> getGames() {
        return gameList;
    }

    public void setGames(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Methods

    public void add(Game game) {
        gameList.add(game);
    }

    public void remove(Game game) {
        gameList.remove(game);
    }



    public void modifyGame(){
        //implement
    };

    public Game getGame (int pos){
       try{
           return gameList.get(pos);
       }

       catch (Exception e){
           return null;
       }
    }

    @Override
    public void sortName() {
        gameList.sort(Comparator.comparing(Game::getTitle));
    }

    @Override
    public void sortFavorite() {
        gameList.sort(Comparator.comparing(Game::getFavorite).reversed());
    }

    @Override
    public void sortLastTime() {
        gameList.sort(Comparator.comparing(Game::getLastTime));
    }

    @Override
    public void sortPlayCount() {
    gameList.sort(Comparator.comparing(Game::getPlayCount).reversed());
    }
}
