package com.gamehub.models;

import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONObject;

/**
 * Clase Post que representa un post en la aplicación.
 * Implementa la interfaz JsonConvertible para permitir la conversión a JSON.
 */
public class Post implements JsonConvertible {
    protected String message; // Mensaje del post
    protected boolean fav = false; // Indica si el post es favorito
    protected String user; // Usuario que creó el post

    /**
     * Constructor que crea un Post con un mensaje.
     *
     * @param message El mensaje del post.
     */
    public Post(String message) {
        this.message = message;
    }

    /**
     * Constructor que crea un Post con un usuario y un mensaje.
     *
     * @param user    El usuario que creó el post.
     * @param message El mensaje del post.
     */
    public Post(String user, String message) {
        this.message = message;
        this.user = user;
    }

    /**
     * Obtiene el usuario que creó el post.
     *
     * @return El usuario del post.
     */
    public String getUser () {
        return user;
    }

    /**
     * Establece el usuario que creó el post.
     *
     * @param user El usuario del post.
     */
    public void setUser (String user) {
        this.user = user;
    }

    /**
     * Establece el mensaje del post.
     *
     * @param message El mensaje del post.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Establece si el post es favorito.
     *
     * @param fav true si el post es favorito, false en caso contrario.
     */
    public void setFav(boolean fav) {
        this.fav = fav;
    }

    /**
     * Devuelve una representación en cadena del post.
     *
     * @return Una cadena que representa el post.
     */
    @Override
    public String toString() {
        return message + ": " + user;
    }

    /**
     * Convierte el post a un objeto JSON.
     *
     * @return Un JSONObject que representa el post.
     */
    @Override
    public JSONObject toJson() {
        JSONObject post = new JSONObject();
        post.put("message", message);
        post.put("fav", fav);
        post.put("user", user);
        return post;
    }
}