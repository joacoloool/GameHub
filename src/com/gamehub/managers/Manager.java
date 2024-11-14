package com.gamehub.managers;
import com.gamehub.enums.AchievType;
import com.gamehub.exceptions.DuplicateElementException;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.models.Achievement;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Manager implements JsonConvertible {

    protected ArrayList<User> users;
    protected HashMap<AchievType, ArrayList<Achievement>> achievement;


    //Constructors

    public Manager() {
        users = new ArrayList<>();
        achievement = new HashMap<>();
        createAchievements();
    }

    //Getters and setters

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //Methods


    public void addUser(User user) {
        users.add(user);
    }


    private void createAchievements() {

        // Se crean los arreglos que van en el hashmap
        ArrayList<Achievement> gameLaunches = new ArrayList<>();
        ArrayList<Achievement> games = new ArrayList<>();
        ArrayList<Achievement> posts = new ArrayList<>();

        // Creamos cada tipo de achievement
        Achievement achievementC1 = new Achievement("Dame Masa", "Open your first game and start the adventure!", AchievType.GAME_LAUNCHES, 1);
        Achievement achievementC2 = new Achievement("Casi Main", "Open any game 10 times.", AchievType.GAME_LAUNCHES, 10);
        Achievement achievementC3 = new Achievement("Tryhard", "Open a game 50 times.", AchievType.GAME_LAUNCHES, 50);
        Achievement achievementC4 = new Achievement("Full Vicio", "Open games 100 times in total.", AchievType.GAME_LAUNCHES, 100);
        Achievement achievementC5 = new Achievement("Gordo Virgo", "Open a game 500 times.", AchievType.GAME_LAUNCHES, 500);
        Achievement achievementG1 = new Achievement("Newbie", "Add your first Steam game.", AchievType.GAMES, 1);
        Achievement achievementG2 = new Achievement("Empezando el Vicio", "Get 10 Steam games.", AchievType.GAMES, 10);
        Achievement achievementG3 = new Achievement("Ballena Jr.", "Reach 50 Steam games.", AchievType.GAMES, 50);
        Achievement achievementG4 = new Achievement("Biblioteca Insana", "Get to 100 games.", AchievType.GAMES, 100);
        Achievement achievementG5 = new Achievement("Archivador Supremo", "Reach 200 games.", AchievType.GAMES, 200);
        Achievement achievementG6 = new Achievement("Fachabiblioteca", "Surpass 500 games.", AchievType.GAMES, 500);
        Achievement achievementP1 = new Achievement("Primer Bait", "Create your first post.", AchievType.POSTS, 1);
        Achievement achievementP2 = new Achievement("Baitmaster", "Reach 10 posts.", AchievType.POSTS, 10);
        Achievement achievementP3 = new Achievement("Mini Influencer", "Get to 50 posts.", AchievType.POSTS, 50);
        Achievement achievementP4 = new Achievement("Forero Facha", "Publish 100 posts.", AchievType.POSTS, 100);
        Achievement achievementP5 = new Achievement("Foro Sensei", "Reach 500 posts.", AchievType.POSTS, 500);
        Achievement achievementP6 = new Achievement("Taringuero", "Surpass 1000 posts.", AchievType.POSTS, 1000);

        // Cargamos en cada arraylist sus respectivos logros
        Collections.addAll(gameLaunches, achievementC1, achievementC2, achievementC3, achievementC4, achievementC5);
        Collections.addAll(games, achievementG1, achievementG2, achievementG3, achievementG4, achievementG5, achievementG6);
        Collections.addAll(posts, achievementP1, achievementP2, achievementP3, achievementP4, achievementP5, achievementP6);

        // Cargamos en el HashMap los arreglos
        achievement.put(AchievType.GAME_LAUNCHES, gameLaunches);
        achievement.put(AchievType.GAMES, games);
        achievement.put(AchievType.POSTS, posts);
    }

    public HashMap<AchievType, ArrayList<Achievement>> getAchievement() {return achievement;}
    public void setAchievement(HashMap<AchievType, ArrayList<Achievement>> achievement) {this.achievement = achievement;}

    public void verifyAchievements() {
        for (User user : users) {
            for (Achievement achievement : achievement.get(AchievType.POSTS)) {
                if (achievement.checkCondition(user.getNumberOfPost()) && !user.getMyAchievements().contains(achievement)) {
                    try {
                        user.addAchievement(achievement);
                    }
                    catch (DuplicateElementException e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
            }
            for (Achievement achievement : achievement.get(AchievType.GAMES)) {
                if (achievement.checkCondition(user.getGamesQuant()) && !user.getMyAchievements().contains(achievement)) {
                    try {
                        user.addAchievement(achievement);
                    }
                    catch (DuplicateElementException e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
            }
            for (Achievement achievement : achievement.get(AchievType.GAME_LAUNCHES)) {
                if (achievement.checkCondition(user.getGameLaunches()) && !user.getMyAchievements().contains(achievement)) {
                    try {
                        user.addAchievement(achievement);
                    }
                    catch (DuplicateElementException e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    //Json


    @Override
    public String toString() {
        return "com.gamehub.managers.Manager{" +
                "users=" + users +
                ", achievement=" + achievement +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject manager = new JSONObject();
        manager.put("users", JsonUtil.toJsonArray(users));
        manager.put("achievement", JsonUtil.achievementsToJSONArray(achievement));

        return manager;
    }
}
