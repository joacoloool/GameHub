import E.AchievType;
import E.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class JsonUtil {

//Guardar archivo

                                    //USER
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
            user.put("myAchievements", MyachievementsToJSONArray(u.getMyAchievements()));
            user.put("friends", friendsToJSONArray(u.getFriends()));
            user.put("gameList", gamestoJSONArray(u.getGameList()));

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public static JSONArray UsersToJSONArray(ArrayList<User> userArr) {
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

                                //GAME TO JSON

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
            game.put("countID", Game.getCountID());
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

    public static JSONArray gamestoJSONArray(ArrayList<Game> games) {
        JSONArray g = new JSONArray();
        for (Game e : games) {
            g.put(gameToJSONObject(e));
        }
        return g;
    }

                                //achievement

    public static JSONObject achievementToJSONObject(Achievement a) {
        JSONObject achievement = new JSONObject();
        try {
            achievement.put("id", a.getId());
            achievement.put("name", a.getName());
            achievement.put("description", a.getDescription());
            achievement.put("type", a.getType());
            achievement.put("condition", a.getCondition());
            achievement.put("count", Achievement.getCount());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return achievement;
    }
    public static JSONArray MyachievementsToJSONArray(ArrayList<Achievement> a ) {
        JSONArray achievements = new JSONArray();
        for (Achievement e : a) {
            achievements.put(achievementToJSONObject(e));
        }
        return achievements;
    }

    public static JSONArray achievementsToJSONArray(HashMap<AchievType, ArrayList<Achievement>> achievementsMap) {
        JSONArray achievements = new JSONArray();

        // Iteramos sobre las entradas del HashMap
        for (ArrayList<Achievement> achievementList : achievementsMap.values()) {
            for (Achievement achievement : achievementList) {
                achievements.put(achievementToJSONObject(achievement));
            }
        }

        return achievements;
    }

                                    //Friends

    public static JSONArray friendsToJSONArray(TreeSet<Integer> f) {
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

                                     //Manager
    public static JSONObject ManagerToJSONObject(Manager m) {
        JSONObject manager = new JSONObject();
        manager.put("users", UsersToJSONArray(m.getUsers()));
        manager.put("achievement", achievementsToJSONArray(m.getAchievement()));

        return manager;
    }


    public static HashMap<AchievType, ArrayList<Achievement>> JsonToManagerMap(JSONArray m) {
        HashMap<AchievType, ArrayList<Achievement>> manager = new HashMap<>();

        for (int i = 0; i < m.length(); i++) {
            JSONObject achievementJson = m.getJSONObject(i);
            AchievType type = AchievType.valueOf(achievementJson.getString("type"));
            Achievement achievement = JSONObjectToAchievement(achievementJson);


            if (!manager.containsKey(type)) {
                manager.put(type, new ArrayList<Achievement>());
            }
            manager.get(type).add(achievement);
        }

        return manager;
    }

                                        //Post
    public static JSONObject postToJSONObject(Post p) {

        JSONObject post = new JSONObject();
        post.put("message", p.getMessage());
        post.put("fav", p.getFav());

        return post;
    }
    public static JSONArray postToJSONArray(ArrayList<Post> p) {
        JSONArray posts = new JSONArray();
        for (Post post : p) {
            posts.put(postToJSONObject(post));
        }
        return posts;
    }

                                    //Feed

    public static JSONObject feedtoJsonObject(Feed f) {
        JSONObject feed = new JSONObject();
        feed.put("posts", postToJSONArray(f.getPosts()));
        return feed;
    }
    protected String message;
    protected int likes = 0;
    protected boolean fav = false;



    //CARGAR ARCHIVO

    public static Manager JSONtoManager(JSONObject m) {
        Manager manager = new Manager();

        manager.setUsers(JSONToUsers(m.getJSONArray("users")));
        manager.setAchievement(JsonToManagerMap(m.getJSONArray("achievement")));
        return manager;
    }

    //Json a object
    public static Feed JSONToFeed(JSONObject f){
        Feed feed = new Feed();
        feed.setPosts(JSONtoPosts(f.getJSONArray("post")));
        return feed;
    }


    public static Post JSONtoPost(JSONObject m) {
        Post post = new Post("");
        post.setMessage(m.getString("message"));
        post.setFav(m.getBoolean("fav"));
        return post;
    }

    public static ArrayList<Post> JSONtoPosts(JSONArray m) {
       ArrayList<Post> posts = new ArrayList<>();
       for (int i = 0; i < m.length(); i++) {
           posts.add(JSONtoPost(m.getJSONObject(i)));
       }
       return posts;
    }

    public static User JSONToUser(JSONObject u) {
        User user = new User();
        user.setId(u.getInt("id"));
        user.setName(u.getString("name"));
        user.setDescription(u.getString("description"));
        user.setFavoriteAchievement(u.getInt("favoriteAchievements"));
        user.setMyAchievements(JSONArrayToAchievements(u.getJSONArray("myAchievements")));
        user.setOpenGameCounter(u.getInt("openGameCounter"));
        user.setGameList(JSONArrayToGames(u.getJSONArray("gameList")));
        user.setFriends(JSONtoFriends(u.getJSONArray("friends")));


        return user;
    }

    public static ArrayList<User> JSONToUsers(JSONArray u) {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < u.length(); i++) {
            users.add(JSONToUser(u.getJSONObject(i)));
        }
        return users;
    }

    public static TreeSet<Integer> JSONtoFriends(JSONArray f) {
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

    public static HashMap<AchievType, ArrayList<Achievement>> JSONArrayToAchievementsHash(JSONArray a) {
        HashMap<AchievType, ArrayList<Achievement>> achievementMap = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            try {
                JSONObject jsonObject = a.getJSONObject(i);
                Achievement achievement = JSONObjectToAchievement(jsonObject);

                // Obtenemos el tipo de logro
                AchievType type = achievement.getType();

                // Añadimos el logro a la lista correspondiente en el HashMap
                achievementMap.putIfAbsent(type, new ArrayList<>());
                achievementMap.get(type).add(achievement);

            } catch (JSONException e) {
                System.out.println("Error al convertir JSONObject a Achievement: " + e.getMessage());
            }
        }

        return achievementMap;
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
        Game.setCountID(u.getInt("countID"));
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
    ///GUARDAR Y CARGAR
    public static void guardar(JSONObject a){
        try{
            FileWriter save = new FileWriter("manager.json");
            save.write(a.toString());
            save.flush();
            save.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void sobreescribir(String nombreArchivo, JSONObject contenido) {
        File file = new File(nombreArchivo);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(contenido.toString(2)); // Escribe el contenido formateado
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addInfo(String nombreArchivo, String contenido) {

        File file = new File(nombreArchivo);

        try{
            PrintWriter salida = new PrintWriter(new FileWriter(file, true));
            salida.println(contenido);
            salida.close();
            System.out.println("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONTokener readWithTokener(String archivo){
        JSONTokener token = null;
        try{
            token = new JSONTokener(new FileReader(archivo));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return token;
    }
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
            e.printStackTrace();
            return null; // Retornar null si hay un error de lectura
        }
        // Convertimos el contenido JSON en un objeto Manager

        Manager manager = new Manager();
        try {
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            manager = JSONtoManager(jsonObject);
        } catch (Exception e) {
            System.out.println("Error al convertir JSON a Manager: " + e.getMessage());
            return null;
        }
        return manager;


    }

}