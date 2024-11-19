package com.gamehub.models;

import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.exceptions.UyMeLlameAmiMismoException;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.interfaces.SortTool;
import com.gamehub.utils.ImageFormatter;
import com.gamehub.utils.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.*;

/**
 * CLASE USUARIO
 */
public class User implements SortTool<Game>, JsonConvertible, Comparable<User> {
    protected int id;
    protected ArrayList<Game> gameList;
    protected String name = "";
    protected String nickname = "";
    protected String password = "";
    protected int gamesQuant = 0;
    protected String description = "";
    protected Feed feed;
    protected TreeSet<String> friends;
    private TreeSet<Achievement> myAchievements;
    protected int favoriteAchievement = 0;
    protected static int count = 0;
    private Icon profileImage;


    public User() {

        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new TreeSet<>();
        this.id = count;
        this.nickname = name;
        count++;

    }

    //Uses in Friend. why? because need only name for searches a user in manager
    public User(String name)
    {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new TreeSet<>();
        this.id = count;
        this.password = password;
        this.nickname = name;

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

    public TreeSet<String> getFriends() {
        return friends;
    }

    public String getDescription() {
        return description;
    }

    public TreeSet<Achievement> getMyAchievements() {
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

    public void setName(TreeSet<String> friends) {
        this.friends = friends;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public int getGamesQuant() {
        return gamesQuant;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void setFriends(TreeSet<String> friends) {
        this.friends = friends;
    }

    public void setMyAchievements(TreeSet<Achievement> myAchievements) {
        this.myAchievements = myAchievements;
    }

    public Icon getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Icon profileImage) {
        this.profileImage = profileImage;
    }
    //Methods

    /**
     * Retorna la cantidad de publicaciones en el feed del usuario
     * utiliza el metodo del objeto feed para
     */
    public int getNumberOfPost() {
        return feed.getPosts().size();
    }

    /**
     * Calcula el total de lanzamientos de un juego en la lista del usuario
     * Suma los lanzamientos de cada jeugo en la lista y devuelve el total
     */
    public int getGameLaunches() {
        int num = 0;
        for (Game game : gameList) {
            num = +game.getGameLaunches();
        }
        return num;
    }

    /**
     * Agrega un logro a la lista de logros del usuario
     * Si el logro ya exisite lanza exception
     */
    public void addAchievement(Achievement achievement) throws DuplicateElementException {
        if (!myAchievements.add(achievement)) {
            throw new DuplicateElementException("This element already exist");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void deleteGame(Game game) {
        gameList.remove(game); //Agregar thrhow e non exist
        gamesQuant--;
    }

    public void addGame(Game game) throws DuplicateElementException {
        if (gameList.contains(game)) {
            throw new DuplicateElementException("This element already exist");
        } else {
            gameList.add(game);
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

    public void saveProfilePicture(){
        String pathName = name + "_pf";
        ImageFormatter.saveProfileImageToFile(pathName,profileImage);
    }

    public void addFriend(String name) throws UyMeLlameAmiMismoException {
        if (!this.name.equals(name)){
            friends.add(name);
        }
        else{
            throw new UyMeLlameAmiMismoException("Uy me llame a mi mismo");
        }
    }

    public void deleteFriend(String id) {
        try {
            if (!friends.remove(id)) {
                System.out.println("El amigo no está en la lista");
            }
        } catch (NullPointerException e) {
            System.out.println("La lista de amigos no ha sido inicializada");
        }
    }

    public boolean searchFriend(String id) {
        if (friends.contains(id)) {
            return true;
        }
        return false;
    }

    public void saveAllImages(){
            saveProfilePicture();

        for (Game game : gameList){
            game.saveImageGame();
            game.saveHeader();
        }
    }

    public void loadProfileImage(){
        String pathName = name + "_pf";
        this.profileImage = ImageFormatter.loadProfileImageFromFile(pathName);
    }

    public void loadAllImages()
    {
        loadProfileImage();
        for (Game game: gameList)
        {
            game.loadImageGame();
            game.loadHeaderGame();
        }
    }


    public Game getLastPlayed() {
        Game mayor = null;
        if (!gameList.isEmpty()) {
            mayor = gameList.getFirst();
            for (Game game : gameList) {
                if (game.lastTime.after(mayor.lastTime)) {
                    mayor = game;
                }
            }
        }
        return mayor;
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

    public void createPost(String str) {
        feed.createPost(str,name);
    }

    public void deletePost(int i) {
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
                ", favoriteAchievement=" + favoriteAchievement;
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
            user.put("password", password);
            if (nickname.isEmpty()){
                user.put("nickname",name);
            }
            else{
                user.put("nickname", nickname);
            }


            // JSON Array
            user.put("feed", feed.toJson());


            JSONArray friendsJson = new JSONArray();
            for (String friend : friends) {
                friendsJson.put(friend);
            }

            user.put("friend", friendsJson);
            user.put("gameList", JsonUtil.toJsonArray(gameList));

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }


    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }
}
