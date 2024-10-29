import E.AchievType;
import java.util.ArrayList;
import java.util.HashMap;


public class Manager {

    protected ArrayList<User> users;
    protected HashMap <AchievType,ArrayList<Achievement>> achievement;


    //Constructors

    public Manager() {
        users = new ArrayList<>();
        achievement = new HashMap<>();
        createAchievement();

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



    private void createAchievement(){
        //Se crean los arreglos que van en el hashmap
        ArrayList<Achievement> Cplays = new ArrayList<>();
        ArrayList<Achievement> Games = new ArrayList<>();
        ArrayList<Achievement> Posts = new ArrayList<>();

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

        Cplays.add(achievementC1);
        Cplays.add(achievementC2);
        Cplays.add(achievementC3);
        Cplays.add(achievementC4);
        Cplays.add(achievementC5);

        Games.add(achievementG1);
        Games.add(achievementG2);
        Games.add(achievementG3);
        Games.add(achievementG4);
        Games.add(achievementG5);
        Games.add(achievementG6);

        Posts.add(achievementP1);
        Posts.add(achievementP2);
        Posts.add(achievementP3);
        Posts.add(achievementP4);
        Posts.add(achievementP5);
        Posts.add(achievementP6);

    //Cargamos en el HashMap los arreglos

        achievement.put(AchievType.CPLAYS,Cplays);
        achievement.put(AchievType.GAMES,Games);
        achievement.put(AchievType.POSTS,Posts);

    }

    public HashMap<AchievType, ArrayList<Achievement>> getAchievement() {
        return achievement;
    }

    public void setAchievement(HashMap<AchievType, ArrayList<Achievement>> achievement) {
        this.achievement = achievement;
    }

    public void verifyAchievements()
    {
        for (User user: users)
        {
            for (Achievement achievement: achievement.get(AchievType.POSTS))
            {
               if(achievement.checkCondition(user.feed.posts.size()) && !user.myAchievements.contains(achievement))
                {
                    user.myAchievements.add(achievement);
                    System.out.println(3);
                }
            }
            for (Achievement achievement: achievement.get(AchievType.GAMES))
            {
                if(achievement.checkCondition(user.gameList.size()) && !user.myAchievements.contains(achievement))
                {
                    user.myAchievements.add(achievement);
                    System.out.println(1);
                }
            }
            for(Achievement achievement: achievement.get(AchievType.CPLAYS))
            {
                if(achievement.checkCondition(user.openGameCounter) && !user.myAchievements.contains(achievement))
                {
                    user.myAchievements.add(achievement);
                    System.out.println(2);
                }
            }
        }
    }




    public void modify() {
        //implement
    }

    @Override
    public String toString() {
        return "Manager{" +
                "users=" + users +
                ", achievement=" + achievement +
                '}';
    }
}
