import E.AchievType;
import org.json.JSONObject;

import java.util.ArrayList;
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

        //Se crean los arreglos que van en el hashmap
        ArrayList<Achievement> cPlays = new ArrayList<>();
        ArrayList<Achievement> games = new ArrayList<>();
        ArrayList<Achievement> posts = new ArrayList<>();

        //Creamos cada tipo de achievement

        Achievement achievementC1 = new Achievement("Dame Masa", "Open your first game and start the adventure!", AchievType.CPLAYS, 1);
        Achievement achievementC2 = new Achievement("Casi Main", "Open any game 10 times.", AchievType.CPLAYS, 10);
        Achievement achievementC3 = new Achievement("Tryhard", "Open a game 50 times.", AchievType.CPLAYS, 50);
        Achievement achievementC4 = new Achievement("Full Vicio", "Open games 100 times in total.", AchievType.CPLAYS, 100);
        Achievement achievementC5 = new Achievement("Gordo Virgo", "Open a game 500 times.", AchievType.CPLAYS, 500);

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

        cPlays.add(achievementC1);
        cPlays.add(achievementC2);
        cPlays.add(achievementC3);
        cPlays.add(achievementC4);
        cPlays.add(achievementC5);

        games.add(achievementG1);
        games.add(achievementG2);
        games.add(achievementG3);
        games.add(achievementG4);
        games.add(achievementG5);
        games.add(achievementG6);

        posts.add(achievementP1);
        posts.add(achievementP2);
        posts.add(achievementP3);
        posts.add(achievementP4);
        posts.add(achievementP5);
        posts.add(achievementP6);


        //Cargamos en el HashMap los arreglos

        achievement.put(AchievType.CPLAYS, cPlays);
        achievement.put(AchievType.GAMES, games);
        achievement.put(AchievType.POSTS, posts);
    }

    public HashMap<AchievType, ArrayList<Achievement>> getAchievement() {return achievement;}
    public void setAchievement(HashMap<AchievType, ArrayList<Achievement>> achievement) {this.achievement = achievement;}

    public void verifyAchievements() {
        for (User user : users) {
            for (Achievement achievement : achievement.get(AchievType.POSTS)) {
                if (achievement.checkCondition(user.feed.posts.size()) && !user.myAchievements.contains(achievement)) {
                    user.myAchievements.add(achievement);
                    System.out.println(3);
                }
            }
            for (Achievement achievement : achievement.get(AchievType.GAMES)) {
                if (achievement.checkCondition(user.gameList.size()) && !user.myAchievements.contains(achievement)) {
                    user.myAchievements.add(achievement);
                    System.out.println(1);
                }
            }
            for (Achievement achievement : achievement.get(AchievType.CPLAYS)) {
                if (achievement.checkCondition(user.openGameCounter) && !user.myAchievements.contains(achievement)) {
                    user.myAchievements.add(achievement);
                    System.out.println(2);
                }
            }
        }
    }

    //Json


    @Override
    public String toString() {
        return "Manager{" +
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
