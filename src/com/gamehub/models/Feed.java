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

    private void pinPost(Post post) {

        if (posts.contains(post))
        {
            if (!post.getFav())
            {
                posts.remove(post);
                for (int i = 0; i < 3; i++)
                {
                    if (!posts.get(i).getFav() && !posts.get(i).equals(post))
                    {
                        posts.set(i, post);
                        post.setFav(true);
                        break;
                    }
                }
            }

        }
    }

    private void unpinPost (Post post){
        if (posts.contains(post))
        {
            if (post.getFav()) {
                posts.remove(post);
                posts.addLast(post);
                post.setFav(false);
            }
        }
    }
    public void togglePinPost(Post post)
    {
        if (post.getFav())
        {
            unpinPost(post);
        }
        else{
            pinPost(post);
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
