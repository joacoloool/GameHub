package com.gamehub.utils;

import com.gamehub.models.*;
import com.gamehub.enums.*;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.managers.Manager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Clase JsonUtil que proporciona métodos para convertir objetos a JSON y viceversa.
 */
public class JsonUtil {

    /**
     * Convierte una colección de objetos en un JSONArray.
     *
     * @param arr La colección de objetos a convertir.
     * @return Un JSONArray que representa la colección de objetos.
     */
    public static JSONArray toJsonArray(Collection<?> arr) {
        JSONArray jsonArray = new JSONArray();
        for (Object o : arr) {
            if (o instanceof JsonConvertible) {
                jsonArray.put(((JsonConvertible) o).toJson());
            }
        }
        return jsonArray;
    }

    /**
     * Convierte un mapa de logros en un JSONArray.
     *
     * @param achievementsMap Un HashMap que contiene logros agrupados por tipo.
     * @return Un JSONArray que representa los logros.
     */
    public static JSONArray achievementsToJSONArray(HashMap<AchievType, ArrayList<Achievement>> achievementsMap) {
        JSONArray achievements = new JSONArray();

        // Iteramos sobre las entradas del HashMap
        for (ArrayList<Achievement> achievementList : achievementsMap.values()) {
            for (Achievement achievement : achievementList) {
                achievements.put(achievement.toJson());
            }
        }
        return achievements;
    }

    /**
     * Convierte un JSONArray en un HashMap de logros agrupados por tipo.
     *
     * @param m El JSONArray que contiene los logros.
     * @return Un HashMap que mapea tipos de logros a listas de logros.
     */
    public static HashMap<AchievType, ArrayList<Achievement>> JsonToManagerMap(JSONArray m) {
        HashMap<AchievType, ArrayList<Achievement>> manager = new HashMap<>();

        for (int i = 0; i < m.length(); i++) {
            JSONObject achievementJson = m.getJSONObject(i);
            AchievType type = AchievType.valueOf(achievementJson.getString("type"));
            Achievement achievement = JSONObjectToAchievement(achievementJson);

            if (!manager.containsKey(type)) {
                manager.put(type, new ArrayList<>());
            }
            manager.get(type).add(achievement);
        }
        return manager;
    }

    /**
     * Convierte un JSONObject en un Manager.
     *
     * @param m El JSONObject que contiene la información del Manager.
     * @return Un objeto Manager.
     */
    public static Manager JSONtoManager(JSONObject m) {
        Manager manager = new Manager();
        manager.setUsers(JSONToUsers(m.getJSONArray("users")));
        manager.setAchievement(JsonToManagerMap(m.getJSONArray("achievement")));
        return manager;
    }

    /**
     * Convierte un JSONObject en un Feed.
     *
     * @param f El JSONObject que contiene la información del Feed.
     * @return Un objeto Feed.
     */
    public static Feed JSONToFeed(JSONObject f) {
        Feed feed = new Feed();
        feed.setPosts(JSONtoPosts(f.getJSONArray("post")));
        return feed;
    }

    /**
     * Convierte un JSONObject en un Post.
     *
     * @param m El JSONObject que contiene la información del Post.
     * @return Un objeto Post.
     */
    public static Post JSONtoPost(JSONObject m) {
        Post post = new Post("");
        post.setMessage(m.getString("message"));
        post.setFav(m.getBoolean("fav"));
        post.setUser (m.getString("user"));
        return post;
    }

    /**
     * Convierte un JSONArray en una lista de Posts.
     *
     * @param m El JSONArray que contiene los Posts.
     * @return Una lista de objetos Post.
     */
    public static ArrayList<Post> JSONtoPosts(JSONArray m) {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < m.length(); i++) {
            posts.add(JSONtoPost(m.getJSONObject(i)));
        }
        return posts;
    }

    /**
     * Convierte un JSONObject en un User.
     *
     * @param u El JSONObject que contiene la información del User.
     * @return Un objeto User.
     */
    public static User JSONToUser (JSONObject u) {
        User user = new User();
        user.setId(u.getInt("id"));
        user.setPassword(u.getString("password"));
        user.setName(u .getString("name"));
        user.setDescription(u.getString("description"));
        user.setFavoriteAchievement(u.getInt("favoriteAchievements"));
        user.setGamesQuant(u.getInt("gamesQuant"));
        user.setGameList(JSONArrayToGames(u.getJSONArray("gameList")));
        user.setFriends(JSONtoFriends(u.getJSONArray("friend")));
        user.setNickname(u.getString("nickname"));
        user.setFeed(JSONToFeed(u.getJSONObject("feed")));
        return user;
    }

    /**
     * Convierte un JSONArray en un conjunto de Users.
     *
     * @param u El JSONArray que contiene los Users.
     * @return Un TreeSet de objetos User.
     */
    public static TreeSet<User> JSONToUsers(JSONArray u) {
        TreeSet<User> users = new TreeSet<>();
        for (int i = 0; i < u.length(); i++) {
            users.add(JSONToUser (u.getJSONObject(i)));
        }
        return users;
    }

    /**
     * Convierte un JSONArray en un conjunto de amigos.
     *
     * @param f El JSONArray que contiene los amigos.
     * @return Un TreeSet de cadenas que representan los amigos.
     */
    public static TreeSet<String> JSONtoFriends(JSONArray f) {
        TreeSet<String> friends = new TreeSet<>();
        for (int i = 0; i < f.length(); i++) {
            friends.add(f.getString(i));
        }
        return friends;
    }

    /**
     * Convierte un JSONObject en un Achievement.
     *
     * @param a El JSONObject que contiene la información del Achievement.
     * @return Un objeto Achievement.
     */
    public static Achievement JSONObjectToAchievement(JSONObject a) {
        Achievement ach = new Achievement();
        ach.setId(a.getInt("id"));
        ach.setName(a.getString("name"));
        ach.setDescription(a.getString("description"));
        ach.setCondition(a.getInt("condition"));
        Achievement.setCount(a.getInt("count"));
        AchievType type = AchievType.valueOf(a.getString("type"));
        ach.setType(type);
        return ach;
    }

    /**
     * Convierte un JSONObject en un Game.
     *
     * @param u El JSONObject que contiene la información del Game.
     * @return Un objeto Game.
     */
    public static Game JSONToGame(JSONObject u) {
        Game game = new Game();
        game.setGameLaunches(u.getInt("gameLaunches"));
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
        Game.setCountID(u.getInt("countID"));
        game.setIcon(game.extractIcon());
        return game;
    }

    /**
     * Convierte un JSONArray en una lista de Games.
     *
     * @param a El JSONArray que contiene los Games.
     * @return Una lista de objetos Game.
     */
    public static ArrayList<Game> JSONArrayToGames(JSONArray a) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            games.add(JSONToGame(a.getJSONObject(i)));
        }
        return games;
    }

    /**
     * Guarda un JSONObject en un archivo.
     *
     * @param nombreArchivo El nombre del archivo donde se guardará el contenido.
     * @param contenido     El JSONObject que se va a guardar.
     */
    public static void guardar(String nombreArchivo, JSONObject contenido) {
        File file = new File(nombreArchivo);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(contenido.toString(2)); // Escribe el contenido formateado
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee un Manager desde un archivo JSON.
     *
     * @return Un objeto Manager leído del archivo, o null si ocurre un error.
     */
    public static Manager leerManager() {
        File file = new File("manager.json");
        StringBuilder jsonContent = new StringBuilder();

        // Leer el archivo JSON y almacenar el contenido en jsonContent
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea = reader.readLine();
            while (linea != null) {
                jsonContent.append(linea);
                linea = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        Manager manager;
        try {
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            manager = JSONtoManager(jsonObject);
        } catch (Exception e) {
            System.out.println("Error al convertir JSON a com.gamehub.managers.Manager: " + e.getMessage());
            return null;
        }
        return manager;
    }
}