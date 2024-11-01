import E.AchievType;
import I.SortTool;

import java.util.*;

public class User implements SortTool<Game> {
    protected int id;
    protected ArrayList<Game> gameList;
    protected String name;
    protected int openGameCounter = 0;
    protected String description = "";
    protected Feed feed;
    protected TreeSet<Integer> friends;
    protected ArrayList<Achievement> myAchievements;
    protected int favoriteAchievement = 0;
    protected static int count = 0;


    public User() {

        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new ArrayList<>();
        this.id = count;
        count++;

    }

    public User(String name) {
        this.name = name;
        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new ArrayList<>();
        this.id = count;
        count++;
    }
    //Getters and setters


    public int getId() {
        return id;
    }

    public void setOpenGameCounter(int openGameCounter) {
        this.openGameCounter = openGameCounter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addGame(Game game) {
        this.gameList.add(game);
    }

    public void setGameList() {
        this.gameList = gameList;
    }

    public String getName() {
        return name;
    }

    public int getFavoriteAchievement() {
        return favoriteAchievement;
    }

    public void setFavoriteAchievement(int favoriteAchievement) {
        this.favoriteAchievement = favoriteAchievement;
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

    public ArrayList<Achievement> getMyAchievements() {
        return myAchievements;
    }

    public void setMyAchievements() {
        this.myAchievements = myAchievements;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public int getOpenGameCounter() {
        return openGameCounter;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void setFriends(TreeSet<Integer> friends) {
        this.friends = friends;
    }

    public void setMyAchievements(ArrayList<Achievement> myAchievements) {
        this.myAchievements = myAchievements;
    }
//Methods

    public int getCountGame() {
        int num = 0;

        for (Game game : gameList) {
            num = +game.playCount;
        }
        return num;
    }

    public void modifyGame() {
        //implement
    }

    ;

    public Game getGame(int pos) {
        try {
            return gameList.get(pos);
        } catch (Exception e) {
            return null;
        }
    }

    public void addFriend(Integer id) {
        friends.add(id);
    }

    public void deleteFriend(Integer id) {
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

    public void viewFeed() {
        try {
            feed.recorrerFeed();
        } catch (NullPointerException e) {
            System.out.println("El feed no ha sido inicializado");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gameList=" + gameList +
                ", name='" + name + '\'' +
                ", openGameCounter=" + openGameCounter +
                ", description='" + description + '\'' +
                ", feed=" + feed +
                ", friends=" + friends +
                ", myAchievements=" + myAchievements +
                ", favoriteAchievement=" + favoriteAchievement +
                '}';
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
