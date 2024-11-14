package com.gamehub.models;

import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONObject;

public class Post implements JsonConvertible {
    protected String message;
    protected boolean fav = false;

    //Builder
    public Post(String message, boolean fav) {
        this.message = message;
        this.fav = fav;
    }
    public Post(String message) {
        this.message = message;
    }

    //Getters
    public String getMessage() {
        return message;
    }
    public boolean isFav() {
        return fav;
    }
    public boolean getFav() {
        return fav;
    }

    //Setters
    public void setMessage(String message) {
        this.message = message;
    }
    public void setFav(boolean fav) {
        this.fav = fav;
    }

    /// Methods
    @Override
    public String toString() {
        return "com.gamehub.models.Post{" +
                "message='" + message + '\'' +
                ", fav=" + fav +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject post = new JSONObject();
        post.put("message", message);
        post.put("fav", fav);

        return post;
    }


}
