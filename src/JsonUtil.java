import E.AchievType;
import E.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class JsonUtil {

    public static JSONObject userToJSONObject(User u) {
        JSONObject user = new JSONObject();
        try {
            user.put("id", u.getId());
            user.put("name", u.getName());
            user.put("openGameCounter", u.getCountGame());
            user.put("description", u.getDescription());
            user.put("favoriteAchievements", u.getFavoriteAchievement());
            //Json Array
            user.put("feed", feedtoJsonObject(u.getFeed()));
            user.put("myAchievements", achievementsToJSONArray(u.getMyAchievements()));
            user.put("friends", friendsToJsonArray(u.getFriends()));
            user.put("gameList", gamestoJsonArray(u.getGameList()));

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }


    public static JSONArray gamestoJsonArray(ArrayList<Game> games) {
        JSONArray g = new JSONArray();
        for (Game e : games) {
            g.put(gameToJSONObject(e));
        }
        return g;

    }


    public static JSONObject gameToJSONObject(Game u) {
        JSONObject game = new JSONObject();
        try {
            game.put("id", u.getId());
            game.put("title", u.getTitle());
            game.put("description", u.getDescription());
            game.put("favorite", u.getFavorite());
            game.put("genre", u.getGenre());
            game.put("lastTime", u.getLastTime().toString());
            game.put("playCount", u.getPlayCount());
            game.put("path", u.getPath());
            game.put("countID", u.getCountID());
            game.put("icon", u.getIcon());
            game.put("appid", u.getAppid());
            game.put("appidIGDB", u.getAppidIGDB());
            game.put("url", u.getUrl());
            game.put("releaseDate", u.getReleaseDate());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return game;
    }

    public static JSONObject achievementToJSONObject(Achievement a) {
        JSONObject achievement = new JSONObject();
        try {
            achievement.put("id", a.getId());
            achievement.put("name", a.getName());
            achievement.put("description", a.getDescription());
            achievement.put("type", a.getType());
            achievement.put("condition", a.getCondition());
            achievement.put("count", a.getCount());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return achievement;
    }


    public static JSONArray achievementsToJSONArray(ArrayList<Achievement> a) {
        JSONArray achievements = new JSONArray();
        for (Achievement e : a) {
            achievements.put(achievementToJSONObject(e));
        }
        return achievements;
    }


    public static JSONArray friendsToJsonArray(TreeSet<Integer> f) {
        JSONArray friends = new JSONArray();

        try {
            for (Integer i : f) {
                friends.put(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public static JSONObject ManagerToJsonObject(Manager m) {
        JSONObject manager = new JSONObject();
        manager.put("users", arrayUserToJSONArray(m.getUsers()));
        //Implementar achivement hash
        return manager;
    }


    public static JSONObject postToJsonObject(Post p) {

        JSONObject post = new JSONObject();
        post.put("message", p.getMessage());
        post.put("likes", p.getLikes());
        post.put("fav", p.getFav());

        return post;
    }

    public static JSONArray postToJsonArray(ArrayList<Post> p) {
        JSONArray posts = new JSONArray();
        for (Post post : p) {
            posts.put(postToJsonObject(post));
        }
        return posts;
    }

    public static JSONObject feedtoJsonObject(Feed f) {
        JSONObject feed = new JSONObject();
        feed.put("posts", postToJsonArray(f.getPosts()));
        return feed;
    }


    protected String message;
    protected int likes = 0;
    protected boolean fav = false;


    public static JSONArray arrayUserToJSONArray(ArrayList<User> userArr) {
        JSONArray users = new JSONArray();
        try {
            for (User u : userArr) {
                users.put(userToJSONObject(u));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }


    ///GUARDAR ARCHIVO

    public static void guardar(JSONObject a) {
        try {
            FileWriter save = new FileWriter("manager.json");
            save.write(a.toString());
            save.flush();
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Manager leerManager() {
        File file = new File("manager.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea = reader.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    return new Manager(); //arreglar
    }

    //Json a object
    public static User JSONToUser(JSONObject u) {
        User user = new User();
        user.setId(u.getInt("id"));
        user.setName(u.getString("name"));
        user.setDescription(u.getString("description"));
        user.setFavoriteAchievement(JSONObjectToAchievement(u.getJSONObject("favoriteAchievements")));
        user.setMyAchievements(JSONArrayToAchievements(u.getJSONArray("myAchievements")));
        user.setOpenGameCounter(u.getInt("openGameCounter"));
        user.setGameList(JSONArrayToGames(u.getJSONArray("gameList")));
        user.setFriends(JSONtoFriends(u.getJSONArray("friends")));


        return user;
    }

    public static TreeSet<Integer> JSONtoFriends(JSONArray f){
        TreeSet<Integer> friends = new TreeSet<>();

        for (int i = 0; i < f.length(); i++) {
            friends.add(f.getInt(i));
        }
        return friends;
    }

    public static Achievement JSONObjectToAchievement(JSONObject a) {
        Achievement ach = new Achievement();
        ach.setId(a.getInt("id"));
        ach.setName(a.getString("name"));
        ach.setDescription(a.getString("description"));
        ach.setCondition(a.getInt("condition"));
        ach.setCount(a.getInt("count"));

        AchievType type = AchievType.valueOf(a.getString("type"));
        ach.setType(type);
        return ach;
    }

    public static ArrayList<Achievement> JSONArrayToAchievements(JSONArray a) {
        ArrayList<Achievement> ach = new ArrayList<>();

        for (int i = 0; i < a.length(); i++) {
            ach.add(JSONObjectToAchievement(a.getJSONObject(i)));
        }
    return ach;
    }



    public static Game JSONToGame(JSONObject u) {
        Game game = new Game();
        game.setPlayCount(u.getInt("playCount"));

        game.setId(u.getInt("id"));
        game.setAppid(u.getString("appid"));
        game.setAppidIGDB(u.getString("appidIGDB"));
        game.setUrl(u.getString("url"));
        game.setReleaseDate(u.getString("releaseDate"));
        game.setFavorite(u.getBoolean("favorite"));
        game.setDescription(u.getString("description"));
        game.setTitle(u.getString("title"));
        game.setPath(new File(u.getString("path")));
        Genre genre = Genre.valueOf(u.getString("genre"));
        game.setGenre(genre);
        game.setLastTime(Timestamp.valueOf(u.getString("lastTime")));
        //count id
        //Icon
        return game;
    }

    public static ArrayList<Game> JSONArrayToGames(JSONArray a) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            games.add(JSONToGame(a.getJSONObject(i)));
        }
        return games;
    }


}