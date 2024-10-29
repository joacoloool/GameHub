public class Post {
    protected String message;
    protected int likes = 0;
    protected boolean fav = false;

    public Post(String message, int likes, boolean fav) {
        this.message = message;
        this.likes = likes;
        this.fav = fav;
    }

    public Post(String message) {
        this.message = message;
    }

    public Post() {
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

    /// Methods

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", likes=" + likes +
                ", fav=" + fav;
    }
}
