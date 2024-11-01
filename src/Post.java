import java.util.HashSet;

public class Post {
    protected String message;
    protected boolean fav = false;

    public Post(String message, boolean fav) {
        this.message = message;
        this.fav = fav;
    }

    public Post(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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



    /// Methods

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", fav=" + fav +
                '}';
    }
}
