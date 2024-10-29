import E.AchievType;

public class Achievement{

    protected String name = "";
    protected int condition;
    protected String description = "";
    protected AchievType type;
    protected int id;
    protected static int count = 0;

    //Builders
    public Achievement(String name, String description, AchievType type,int condition) {
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.id = count;
        this.type = type;
        count++;
    }

    public boolean checkCondition(int valueCondition){
        if (valueCondition == condition)
        {
            return true;
        }
        else{
            return false;
        }
    }




    //Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public AchievType getType() {
        return type;
    }
    public int getId() {
        return id;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(AchievType type) {
        this.type = type;
    }

    public int compareTo(Achievement otherAchievement) {
        return name.compareTo(otherAchievement.name);
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", id=" + id +
                '}';
    }


}
