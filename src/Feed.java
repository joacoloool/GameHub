import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;

public class Feed implements JsonConvertible{
    protected ArrayList<Post> posts;

    public Feed() {
        this.posts = new ArrayList<>();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // Methods ------------------------------------
    public void createPost(String str) {
        try {
            posts.add(new Post(str));
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

    @Override
            public JSONObject toJson() {
            JSONObject feed = new JSONObject();
            feed.put("posts", JsonUtil.toJsonArray(posts));
            return feed;
        }


//Json



}
