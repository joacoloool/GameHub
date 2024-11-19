package com.gamehub.models;

import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.utils.JsonUtil;
import org.json.JSONObject;
import java.util.ArrayList;

public class Feed implements JsonConvertible {
    protected ArrayList<Post> posts;

    public Feed() {
        this.posts = new ArrayList<>();
    }

    // Getters and Setters
    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // Methods ------------------------------------
    public void createPost(String str,String user) {
        try {
            posts.add(new Post(str,user));
        } catch (NullPointerException  e) {
            System.out.println("El feed de posts no ha sido inicializado");
        }
    }

    public void deletePost(int i) {
        try {
            posts.remove(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("El índice no existe en la lista");
        }

    }

    //Json
    @Override
    public JSONObject toJson() {
        JSONObject feed = new JSONObject();
        feed.put("post", JsonUtil.toJsonArray(posts));
        return feed;
    }
}
