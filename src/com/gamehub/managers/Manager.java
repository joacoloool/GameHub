package com.gamehub.managers;

import com.gamehub.enums.AchievType;
import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.exceptions.NonExistObjectException;
import com.gamehub.exceptions.StringTooShort;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.models.Achievement;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.util.*;

/**
 * Clase Manager que gestiona usuarios y logros en la aplicación.
 * Implementa la interfaz JsonConvertible para permitir la conversión a JSON.
 */
public class Manager implements JsonConvertible {

    /**
     * Colección de usuarios utilizando un TreeSet para mantener el orden.
     */
    protected TreeSet<User> users;

    /**
     * Colección de logros utilizando un HashMap que relaciona tipos de logros con listas de logros.
     */
    protected HashMap<AchievType, ArrayList<Achievement>> achievement;

    /**
     * Constructor que inicializa las colecciones de usuarios y logros.
     */
    public Manager() {
        users = new TreeSet<>();
        achievement = new HashMap<>();
        createAchievements(); // Crea los logros iniciales
    }

    // Getters y Setters

    /**
     * Obtiene la colección de usuarios.
     *
     * @return La colección de usuarios.
     */
    public TreeSet<User> getUsers() {
        return users;
    }

    /**
     * Establece la colección de usuarios.
     *
     * @param users La colección de usuarios a establecer.
     */
    public void setUsers(TreeSet<User> users) {
        this.users = users;
    }

    /**
     * Establece el HashMap de logros.
     *
     * @param achievement El HashMap de logros a establecer.
     */
    public void setAchievement(HashMap<AchievType, ArrayList<Achievement>> achievement) {
        this.achievement = achievement;
    }

    // Métodos

    /**
     * Agrega un nuevo usuario a la colección.
     *
     * @param user El usuario a agregar.
     * @throws DuplicateElementException Si el usuario ya existe.
     * @throws StringTooShort Si la contraseña o el nombre del usuario son demasiado cortos.
     */
    public void addUser (User user) {
        for (User  u : users) {
            if (u.equals(user)) {
                throw new DuplicateElementException("El usuario ya existe");
            }
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 6) {
            throw new StringTooShort("La contraseña es demasiado débil");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new StringTooShort("El usuario no puede estar vacío");
        }
        users.add(user); // Agrega el usuario a la colección
    }

    /**
     * Guarda un usuario modificado en la colección.
     *
     * @param user El usuario modificado.
     */
    public void saveModifiedUser (User user) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User u = iterator.next();
            if (u.getName().equals(user.getName())) {
                iterator.remove(); // Elimina de manera segura el usuario anterior
                users.add(user);   // Añade el nuevo usuario al final
                break;  // No sigas iterando una vez que encuentres y modifiques al usuario
            }
        }
    }

    /**
     * Crea logros y los organiza en el HashMap por tipo de logro.
     */
    public void createAchievements() {
        ArrayList<Achievement> gameLaunches = new ArrayList<>();
        ArrayList<Achievement> games = new ArrayList<>();
        ArrayList<Achievement> posts = new ArrayList<>();

        // Creamos los logros de abrir el launcher
        Achievement achievementC1 = new Achievement("Dame Masa", "Open your first game and start the adventure!", AchievType.GAME_LAUNCHES, 1);
        Achievement achievementC2 = new Achievement("Casi Main", "Open any game 10 times.", AchievType.GAME_LAUNCHES, 10);
        Achievement achievementC3 = new Achievement("Tryhard", "Open a game 50 times.", AchievType.GAME_LAUNCHES, 50);
        Achievement achievementC4 = new Achievement("Full Vicio", "Open games 100 times in total.", AchievType.GAME_LAUNCHES, 100);
        Achievement achievementC5 = new Achievement("Gordo Virgo", "Open a game 500 times.", AchievType.GAME_LAUNCHES, 500);
        // Creamos los logros al añadir juegos
        Achievement achievementG1 = new Achievement("Newbie", "Add your first game.", AchievType.GAMES, 1);
        Achievement achievementG2 = new Achievement("Empezando el Vicio", "Get 10 games.", AchievType.GAMES, 10);
        Achievement achievementG3 = new Achievement("Ballena Jr.", "Reach 50 games.", AchievType.GAMES, 50);
        Achievement achievementG4 = new Achievement("Biblioteca Insana", "Get to 100 games.", AchievType.GAMES, 100);
        Achievement achievementG5 = new Achievement("Archivador Supremo", "Reach 200 games.", AchievType.GAMES, 200);
        Achievement achievementG6 = new Achievement("Fachabiblioteca", "Surpass 500 games.", AchievType.GAMES, 500);
        // Creamos los logros al crear posts
        Achievement achievementP1 = new Achievement("Primer Bait", "Create your first post.", AchievType.POSTS, 1);
        Achievement achievementP2 = new Achievement("Baitmaster", "Reach 10 posts.", AchievType.POSTS, 10);
        Achievement achievementP3 = new Achievement("Mini Influencer", "Get to 50 posts.", AchievType.POSTS, 50);
        Achievement achievementP4 = new Achievement("Forero Facha", "Publish 100 posts.", AchievType.POSTS, 100);
        Achievement achievementP5 = new Achievement("Taringuero", "Reach 500 posts.", AchievType.POSTS, 500);

        // Agregamos los logros a sus respectivos ArrayLists
        Collections.addAll(gameLaunches, achievementC1, achievementC2, achievementC3, achievementC4, achievementC5);
        Collections.addAll(games, achievementG1, achievementG2, achievementG3, achievementG4, achievementG5, achievementG6);
        Collections.addAll(posts, achievementP1, achievementP2, achievementP3, achievementP4, achievementP5);

        // Cargamos los logros en el HashMap
        achievement.put(AchievType.GAME_LAUNCHES, gameLaunches);
        achievement.put(AchievType.GAMES, games);
        achievement.put(AchievType.POSTS, posts);

        // Asignamos iconos a los logros
        int i = 1;
        for (Achievement ach : achievement.get(AchievType.GAMES)) {
            ach.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/achievements/badgeG" + (i) + ".png")));
            i++;
        }
        i = 1;
        for (Achievement ach : achievement.get(AchievType.POSTS)) {
            ach.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/achievements/badgeP" + (i) + ".png")));
            i++;
        }
        i = 1;
        for (Achievement ach : achievement.get(AchievType.GAME_LAUNCHES)) {
            ach.setIcon(new ImageIcon(getClass().getResource("/com/gamehub/images/achievements/badgeC" + (i) + ".jpg")));
            i++;
        }
    }

    /**
     * Verifica los logros de todos los usuarios en función de sus acciones.
     */
    public void verifyAchievements() {
        for (User  user : users) {
            checkAchievementsForType(user, AchievType.POSTS, user.getNumberOfPost());
            checkAchievementsForType(user, AchievType.GAMES, user.getGamesQuant());
            checkAchievementsForType(user, AchievType.GAME_LAUNCHES, user.getGameLaunches());
        }
    }

    private void checkAchievementsForType(User user, AchievType type, int conditionValue) {
        for (Achievement achievement : achievement.get(type)) {
            if (achievement.checkCondition(conditionValue) && !user.getMyAchievements().contains(achievement)) {
                try {
                    user.addAchievement(achievement);
                } catch (DuplicateElementException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Obtiene un usuario por su nombre.
     *
     * @param name El nombre del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public User getUserByName(String name) {
        for (User  user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null; // Retorna null si no se encuentra el usuario
    }

    /**
     * Verifica si un usuario existe en la colección.
     *
     * @param user El usuario a verificar.
     * @return true si el usuario existe, de lo contrario lanza una excepción.
     * @throws NonExistObjectException Si el usuario no existe.
     */
    public boolean containsUser (User user) throws NonExistObjectException {
        if (!users.contains(user)) {
            throw new NonExistObjectException("Este usuario no existe");
        }
        return true; // Retorna true si el usuario existe
    }

    /**
     * Busca un usuario por su nombre y contraseña.
     *
     * @param name     El nombre del usuario.
     * @param password La contraseña del usuario.
     * @return true si se encuentra el usuario con la contraseña correcta, de lo contrario false.
     */
    public Boolean findUser (String name, String password) {
        for (User  user : users) {
            if (user.getName().equals(name) && Objects.equals(user.getPassword(), password)) {
                return true; // Retorna true si se encuentra el usuario
            }
        }
        return false; // Retorna false si no se encuentra el usuario
    }

    /**
     * Guarda todas las imágenes de los usuarios.
     */
    public void saveAllImages() {
        for (User  user : users) {
            user.saveAllImages(); // Llama al método para guardar imágenes de cada usuario
        }
    }

    /**
     * Carga todas las imágenes de los usuarios.
     */
    public void loadAllImages() {
        for (User  user : users) {
            user.loadAllImages(); // Llama al método para cargar imágenes de cada usuario
        }
    }

    /**
     * Representación en cadena de la clase Manager.
     *
     * @return Una cadena que representa el estado del Manager.
     */
    @Override
    public String toString() {
        return "com.gamehub.managers.Manager" +
                "users=" + users +
                ", achievement=" + achievement;
    }

    // Json

    /**
     * Convierte el Manager a un objeto JSON.
     *
     * @return Un JSONObject que representa el Manager.
     */
    @Override
    public JSONObject toJson() {
        JSONObject manager = new JSONObject();
        manager.put("users", JsonUtil.toJsonArray(users)); // Agrega la lista de usuarios al JSON
        manager.put("achievement", JsonUtil.achievementsToJSONArray(achievement)); // Agrega los logros al JSON

        return manager; // Devuelve el objeto JSON
    }
}