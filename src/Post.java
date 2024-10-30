import java.util.HashSet;

public class Post {
    protected String message;
    protected int likes = 0;
    protected boolean fav = false;
    private HashSet<Integer> usersWhoLiked;

    public Post(String message, int likes, boolean fav) {
        this.message = message;
        this.likes = likes;
        this.fav = fav;
    }

    public Post(String message) {
        this.message = message;
        this.usersWhoLiked = new HashSet<>();
    }

    public Post() {
        this.usersWhoLiked = new HashSet<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void likePost() {
        this.likes++;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public boolean getFav(){
        return fav;
    }

    public void likePost(int userId) {
        if (usersWhoLiked.contains(userId)) {
            usersWhoLiked.remove(userId);
            likes--;
        } else {
            usersWhoLiked.add(userId);
            likes++;
        }
    }



    /// Methods

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", likes=" + likes +
                ", fav=" + fav;
    }
}
