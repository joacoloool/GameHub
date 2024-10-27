import I.ListHelper;
import I.SortTool;

import java.util.ArrayList;

public class User implements ListHelper<Game>, SortTool<Game> {
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
    public void sortType(Game game) {

    }

    @Override
    public void sortFavorite(Game game) {

    }

    @Override
    public void sortLastTime(Game game) {

    }

    @Override
    public void sortPlayCount(Game game) {

    }

    @Override
    public void groupGenre(Game game) {

    }


    @Override
    public void delete(Game game) {
        gameList.remove(game);

    }

    @Override
    public void add(Game game) {
        gameList.add(game);

    }
}
