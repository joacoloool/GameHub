package com.gamehub.models;

import com.gamehub.enums.AchievType;
import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Objects;

/**
 * La clase Achievement representa un logro en el contexto de un juego.
 * Cada logro tiene un nombre, descripción, tipo, condición para ser alcanzado,
 * un identificador único y un icono asociado.
 * Esta clase implementa la interfaz JsonConvertible para permitir la conversión
 * a un objeto JSON y también es comparable para permitir la ordenación.
 */
public class Achievement implements JsonConvertible, Comparable<Achievement> {

    protected String name;
    protected int condition;
    protected String description = "";
    protected AchievType type;
    protected int id;
    protected static int count = 0;
    protected Icon icon;

    /**
     * Constructor que inicializa un logro con un nombre, descripción, tipo y condición.
     *
     * @param name        El nombre del logro.
     * @param description La descripción del logro.
     * @param type       El tipo de logro (AchievType).
     * @param condition   La condición que debe cumplirse para alcanzar el logro.
     */
    public Achievement(String name, String description, AchievType type, int condition) {
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.id = count;
        this.type = type;
        count++;
    }

    /**
     * Constructor por defecto que inicializa un logro sin parámetros.
     * Asigna un identificador único basado en el contador estático.
     */
    public Achievement() {
        this.id = count;
        count++;
    }

    /**
     * Verifica si un valor dado cumple con la condición del logro.
     *
     * @param valueCondition El valor a verificar.
     * @return true si el valor cumple con la condición, false en caso contrario.
     */
    public boolean checkCondition(int valueCondition) {
        return valueCondition >= condition;
    }

    // Getters

    /**
     * Obtiene el nombre del logro.
     *
     * @return El nombre del logro.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción del logro.
     *
     * @return La descripción del logro.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene el tipo de logro.
     *
     * @return El tipo de logro (AchievType).
     */
    public AchievType getType() {
        return type;
    }

    /**
     * Obtiene el icono asociado al logro.
     *
     * @return El icono del logro.
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Establece el icono asociado al logro.
     *
     * @param icon El icono a establecer.
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    // Setters

    /**
     * Establece el nombre del logro.
     *
     * @param name El nombre a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece la descripción del logro.
     *
     * @param description La descripción a establecer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Establece el tipo de logro.
     *
     * @param type El tipo a establecer (AchievType).
     */
    public void setType(AchievType type) {
        this.type = type;
    }

    /**
     * Establece el valor del contador de logros.
     *
     * @param count El nuevo valor del contador.
     */
    public static void setCount(int count) {
        Achievement.count = count;
    }

    /**
     * Establece el identificador del logro.
     *
     * @param id El identificador a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece la condición del logro.
     *
     * @param condition La condición a establecer.
     */
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
        return Objects.hashCode(name);
    }

    @Override
    public String toString () {
        return name;
    }

    /**
     * Convierte el logro a un objeto JSON.
     *
     * @return Un objeto JSONObject que representa el logro.
     */
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

    /**
     * Compara este logro con otro logro basado en su nombre.
     *
     * @param o El logro a comparar.
     * @return Un valor negativo, cero o positivo si este logro es menor, igual o mayor que el otro.
     */
    @Override
    public int compareTo(Achievement o) {
        return this.name.compareTo(o.name);
    }
}