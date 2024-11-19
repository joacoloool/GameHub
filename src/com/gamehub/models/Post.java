package com.gamehub.models;

import com.gamehub.interfaces.JsonConvertible;
import org.json.JSONObject;

public class Post implements JsonConvertible {
    protected String message;
    protected boolean fav = false;
    protected String user;

    //Builder
    public Post(String message) {
        this.message = message;
    }

    public Post( String user,String message) {
        this.message = message;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
        return message+": "+ user;
    }

    @Override
    public JSONObject toJson() {
        JSONObject post = new JSONObject();
        post.put("message", message);
        post.put("fav", fav);
        post.put("user", user);

        return post;
    }




}
