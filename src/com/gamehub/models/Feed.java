package com.gamehub.models;

import com.gamehub.exceptions.NonExistObjectException;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.utils.JsonUtil;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Clase Feed que representa un feed de publicaciones en la aplicación.
 * Implementa la interfaz JsonConvertible para permitir la conversión a JSON.
 */
public class Feed implements JsonConvertible {
    protected ArrayList<Post> posts; // Lista de publicaciones en el feed

    /**
     * Constructor que inicializa el feed con una lista vacía de publicaciones.
     */
    public Feed() {
        this.posts = new ArrayList<>();
    }

    // Getters y Setters

    /**
     * Obtiene la lista de publicaciones en el feed.
     *
     * @return La lista de publicaciones.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Establece la lista de publicaciones en el feed.
     *
     * @param posts La lista de publicaciones a establecer.
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // Métodos ------------------------------------

    /**
     * Crea una nueva publicación y la agrega al feed.
     *
     * @param str  El contenido de la publicación.
     * @param user El usuario que crea la publicación.
     */
    public void createPost(String str, String user) {
        try {
            posts.add(new Post(str, user)); // Agrega una nueva publicación a la lista
        } catch (NullPointerException e) {
            System.out.println("El feed de posts no ha sido inicializado"); // Manejo de excepción si el feed no está inicializado
        }
    }

    /**
     * Elimina una publicación del feed en la posición especificada.
     *
     * @param post la publicación a eliminar.
     */
    public void deletePost(Post post) {
        try {
            posts.remove(post); // Elimina la publicación en el índice especificado
        }catch (NonExistObjectException e)
        {
            e.getMessage();
        }
    }

    // Json

    /**
     * Convierte el feed a un objeto JSON.
     *
     * @return Un JSONObject que representa el feed.
     */
    @Override
    public JSONObject toJson() {
        JSONObject feed = new JSONObject(); // Crea un nuevo objeto JSON
        feed.put("post", JsonUtil.toJsonArray(posts)); // Agrega la lista de publicaciones al JSON
        return feed; // Devuelve el objeto JSON
    }
}