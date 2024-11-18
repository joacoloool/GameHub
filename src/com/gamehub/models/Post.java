package com.gamehub.models;

import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONObject;

public class Post implements JsonConvertible {
    protected String message;
    protected boolean fav = false;
    protected User user;

    //Builder
    public Post(String message) {
        this.user = user;
        this.message = message;
        this.fav = fav;
    }

    public Post( User user,String message) {
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
        return user +"\n"+ message;
    }

    @Override
    public JSONObject toJson() {
        JSONObject post = new JSONObject();
        post.put("message", message);
        post.put("fav", fav);
        post.put("user", user.getName());

        return post;
    }


}
