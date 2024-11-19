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
 * Representa un usuario en el sistema de gestión de juegos.
 * Esta clase permite manejar la información del usuario, su lista de juegos,
 * amigos, logros y publicaciones en el feed.
 * Además, implementa funcionalidades para ordenar juegos y convertir a formato JSON.
 */
public class User implements SortTool<Game>, JsonConvertible, Comparable<User> {
    protected int id; // Identificador único del usuario
    protected ArrayList<Game> gameList; // Lista de juegos del usuario
    protected String name = ""; // Nombre del usuario
    protected String nickname = ""; // Apodo del usuario
    protected String password = ""; // Contraseña del usuario
    protected int gamesQuant = 0; // Cantidad de juegos del usuario
    protected String description = ""; // Descripción del usuario
    protected Feed feed; // Feed de publicaciones del usuario
    protected TreeSet<String> friends; // Conjunto de amigos del usuario
    private TreeSet<Achievement> myAchievements; // Logros del usuario
    protected int favoriteAchievement = 0; // Logro favorito del usuario
    protected static int count = 0; // Contador de usuarios
    private Icon profileImage; // Imagen de perfil del usuario

    /**
     * Constructor por defecto que inicializa el usuario con una lista de juegos vacía,
     * un feed vacío, un conjunto de amigos vacío y un conjunto de logros vacío.
     */
    public User() {
        this.gameList = new ArrayList<>();
        this.feed = new Feed();
        this.friends = new TreeSet<>();
        this.myAchievements = new TreeSet<>();
        this.id = count;
        this.nickname = name;
        count++;
    }

    /**
     * Constructor que inicializa el usuario con un nombre.
     *
     * @param name Nombre del usuario.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Constructor que inicializa el usuario con un nombre y una contraseña.
     *
     * @param name     Nombre del usuario.
     * @param password Contraseña del usuario.
     */
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

    // Métodos de acceso (getters y setters)

    public String getName() {
        return name;
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

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void setFriends(TreeSet<String> friends) {
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

    public void setProfileImage(Icon profileImage) {
        this.profileImage = profileImage;
    }

    public Icon getProfileImage() {
        return profileImage;
    }

    // Métodos

    /**
     * Retorna la cantidad de publicaciones en el feed del usuario.
     *
     * @return Número de publicaciones en el feed.
     */
    public int getNumberOfPost() {
        return feed.getPosts().size();
    }

    /**
     * Calcula el total de lanzamientos de los juegos en la lista del usuario.
     * Suma los lanzamientos de cada juego en la lista y devuelve el total.
     *
     * @return Total de lanzamientos de juegos.
     */
    public int getGameLaunches() {
        int num = 0;
        for (Game game : gameList) {
            num += game.getGameLaunches();
        }
        return num;
    }

    /**
     * Agrega un logro a la lista de logros del usuario.
     * Si el logro ya existe, lanza una excepción {@link DuplicateElementException}.
     *
     * @param achievement Logro a agregar.
     * @throws DuplicateElementException Si el logro ya existe en la lista.
     */
    public void addAchievement(Achievement achievement) throws DuplicateElementException {
        if (!myAchievements.add(achievement)) {
            throw new DuplicateElementException("Este elemento ya existe");
        }
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Elimina un juego de la lista del usuario.
     *
     * @param game Juego a eliminar.
     */
    public void deleteGame(Game game) {
        gameList.remove(game); // Agregar throw en caso de que no exista
        gamesQuant--;
    }

    /**
     * Agrega un juego a la lista del usuario.
     * Si el juego ya existe, lanza una excepción {@link DuplicateElementException}.
     *
     * @param game Juego a agregar.
     * @throws DuplicateElementException Si el juego ya existe en la lista.
     */
    public void addGame(Game game) throws DuplicateElementException {
        if (gameList.contains(game)) {
            throw new DuplicateElementException("Este elemento ya existe");
        } else {
            gameList.add(game);
        }
        gamesQuant++;
    }

    /**
     * Guarda la imagen de perfil del usuario en un archivo.
     */
    public void saveProfilePicture() {
        String pathName = name + "_pf";
        ImageFormatter.saveProfileImageToFile(pathName, profileImage);
    }

    /**
     * Agrega un amigo a la lista de amigos del usuario.
     * Si el usuario intenta agregarse a sí mismo, lanza una excepción {@link UyMeLlameAmiMismoException}.
     *
     * @param name Nombre del amigo a agregar.
     * @throws UyMeLlameAmiMismoException Si el usuario intenta agregarse a sí mismo.
     */
    public void addFriend(String name) throws UyMeLlameAmiMismoException {
        if (!this.name.equals(name)) {
            friends.add(name);
        } else {
            throw new UyMeLlameAmiMismoException("Uy, me llamé a mí mismo");
        }
    }

    /**
     * Busca un amigo en la lista de amigos del usuario.
     *
     * @param id Nombre del amigo a buscar.
     * @return Verdadero si el amigo está en la lista, falso en caso contrario.
     */
    public boolean searchFriend(String id) {
        return friends.contains(id);
    }

    /**
     * Guarda todas las imágenes asociadas al usuario y sus juegos.
     */
    public void saveAllImages() {
        saveProfilePicture();
        for (Game game : gameList) {
            game.saveImageGame();
            game.saveHeader();
        }
    }

    /**
     * Carga la imagen de perfil del usuario desde un archivo.
     */
    public void loadProfileImage() {
        String pathName = name + "_pf";
        this.profileImage = ImageFormatter.loadProfileImageFromFile(pathName);
    }

    /**
     * Carga todas las imágenes asociadas al usuario y sus juegos.
     */
    public void loadAllImages() {
        loadProfileImage();
        for (Game game : gameList) {
            game.loadImageGame();
            game.loadHeaderGame();
        }
    }

    /**
     * Obtiene el último juego jugado por el usuario.
     *
     * @return El último juego jugado, o null si no hay juegos en la lista.
     */
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

    /**
     * Crea una publicación en el feed del usuario con el contenido especificado.
     *
     * @param str Contenido de la publicación.
     */
    public void createPost(String str) {
        feed.createPost(str, name);
    }

    /**
     * Crea una publicación en el feed del usuario con el contenido y el nombre especificado.
     *
     * @param str  Contenido de la publicación.
     * @param name Nombre del usuario que realiza la publicación.
     */
    public void createPost(String str, String name) {
        feed.createPost(str, name);
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
        gameList.sort(Comparator.comparing(Game::getLastTime).reversed());
    }

    @Override
    public void sortPlayCount() {
        gameList.sort(Comparator.comparing(Game::getGameLaunches).reversed());
    }

    // Métodos para conversión a JSON

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
            user.put("password", password);
            user.put("nickname", nickname.isEmpty() ? name : nickname);

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