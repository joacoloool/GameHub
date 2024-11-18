package com.gamehub.models;
import com.gamehub.enums.AchievType;
import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Objects;

public class Achievement implements JsonConvertible {

    protected String name;
    protected int condition;
    protected String description = "";
    protected AchievType type;
    protected int id;
    protected static int count = 0;
    protected Icon icon;

    //Builders
    public Achievement(String name, String description, AchievType type, int condition) {
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.id = count;
        this.type = type;
        count++;
    }

    public Achievement() {
        this.id = count;
        count++;
    }

    public boolean checkCondition(int valueCondition) {
     return valueCondition>=condition;
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
    public int getCondition() {
        return condition;
    }
    public static int getCount() {
        return count;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
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
    public static void setCount(int count) {
        Achievement.count = count;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return id == that.id;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "com.gamehub.models.Achievement{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
    //Json
    @Override
    public JSONObject toJson() {
        JSONObject achievement = new JSONObject();
        try {
            achievement.put("id", id);
            achievement.put("name", name);
            achievement.put("description", description);
            achievement.put("type", type);
            achievement.put("condition", condition);
            achievement.put("count", count);
        } catch (JSONException e) {
            System.out.println("No fue posible cargar el JSON.");
        }
        return achievement;
    }
}