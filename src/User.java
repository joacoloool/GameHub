import I.SortTool;

import java.util.*;

public class User implements SortTool<Game> {
    protected int id;
    protected ArrayList<Game> gameList;
    protected String name;
    protected int openGameCounter =0;
    protected String description= "";
    protected Feed feed;
    protected TreeSet<Integer> friends;
    protected ArrayList<Achievement> myAchievements;
    protected Achievement favoriteAchievement;



    public User(String name) {
        this.name = name;
        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new ArrayList<>();
    }
    //Getters and setters

    public void setFavoriteAchievement (Achievement achievement){

        this.favoriteAchievement = achievement;
    }

    public int getId() {
        return id;
    }

    public void setOpenGameCounter(int openGameCounter) {
        this.openGameCounter = openGameCounter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Game> gameList(){
        return gameList;
    }
    public void setGameList(){
        this.gameList = gameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Feed getFeed() {
        return feed;
    }

    public void setName(Feed feed) {
        this.feed = feed;
    }
    public TreeSet<Integer> getFriends() {
        return friends;
    }

    public void setName(TreeSet<Integer> friends) {
        this.friends = friends;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    //Methods

    public int getCountGame()
    {
        int num=0;

        for (Game game: gameList)
        {
            num=+game.playCount;
        }
        return num;
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
    public void addFriend(Integer id){
        friends.add(id);
    }

    public void deleteFriend(Integer id){
        try {
            if (!friends.remove(id)) {
                System.out.println("El amigo no está en la lista");
            }
        } catch (NullPointerException e) {
            System.out.println("La lista de amigos no ha sido inicializada");
        }
    }

    public User viewFriend(ArrayList<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                System.out.println("Amigo encontrado: " + user.getName());
                return user;
            }
        }
        System.out.println("Amigo no encontrado con ID: " + id);
        return null;
    }

    public void viewFeed(){
        try {
            feed.recorrerFeed();
        } catch (NullPointerException e) {
            System.out.println("El feed no ha sido inicializado");
        }
    }

    @Override
    public void sortName() {gameList.sort(Comparator.comparing(Game::getTitle));}

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
