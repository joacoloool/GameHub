package com.gamehub.models;

import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.interfaces.SortTool;
import com.gamehub.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class User implements SortTool<Game>, JsonConvertible {
    protected int id;
    protected ArrayList<Game> gameList;
    protected String name;
    protected int gamesQuant = 0;
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
    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getFavoriteAchievement() {
        return favoriteAchievement;
    }
    public Feed getFeed() {
        return feed;
    }
    public TreeSet<Integer> getFriends() {
        return friends;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<Achievement> getMyAchievements() {
        return myAchievements;
    }
    public ArrayList<Game> getGameList() {
        return gameList;
    }
    //Setters

    public void setGamesQuant(int gamesQuant) {
        this.gamesQuant = gamesQuant;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFavoriteAchievement(int favoriteAchievement) {
        this.favoriteAchievement = favoriteAchievement;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setName(Feed feed) {
        this.feed = feed;
    }
    public void setName(TreeSet<Integer> friends) {
        this.friends = friends;
    }

    public void setDescription(String description) {
        this.description = description;}


    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }
    public int getGamesQuant() {
        return gamesQuant;
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
    public int getNumberOfPost()
    {
        return  feed.getPosts().size();
    }

    public int getGameLaunches() {
        int num = 0;
        for (Game game : gameList) {
            num = +game.getGameLaunches();
        }
        return num;
    }

    public void addAchievement(Achievement achievement) throws DuplicateElementException{
        if (!myAchievements.add(achievement))
        {
            throw new DuplicateElementException("This element already exist");
        }
    }

    public void addGame(Game game)throws DuplicateElementException {
        if (!gameList.add(game))
        {
            throw new DuplicateElementException("This element already exist");
        }
        gamesQuant++;
    }

    public void modifyGame() {
        //implement
    }

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

    public void createPost(String str)
    {
        feed.createPost(str);
    }

    public void deletePost(int i)
    {
        feed.deletePost(i);
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
        gameList.sort(Comparator.comparing(Game::getGameLaunches).reversed());
    }

    //Json
    @Override
    public String toString() {
        return "com.gamehub.models.User{" +
                "id=" + id +
                ", gameList=" + gameList +
                ", name='" + name + '\'' +
                ", gamesQuant=" + gamesQuant +
                ", description='" + description + '\'' +
                ", feed=" + feed +
                ", friends=" + friends +
                ", myAchievements=" + myAchievements +
                ", favoriteAchievement=" + favoriteAchievement +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject user = new JSONObject();
        try {
            user.put("id", id);
            user.put("name", name);
            user.put("gamesQuant", gamesQuant);
            user.put("description", description);
            user.put("favoriteAchievements", favoriteAchievement);

            // JSON Array
            user.put("feed", feed.toJson());
            user.put("myAchievements", JsonUtil.toJsonArray(myAchievements));
            user.put("friends", JsonUtil.toJsonArray(friends));
            user.put("gameList", JsonUtil.toJsonArray(gameList));

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }


}
