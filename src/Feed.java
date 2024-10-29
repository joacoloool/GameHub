import java.util.ArrayList;

public class Feed {
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

    // metodos que seguro hay que borrar -----------------------------------------
    public String recorrerFeed() {

        return posts.toString();
    }

    public Post selectPosPost(int i) {
        return posts.get(i);
    }

    // ------------------------------------------------------------
    public void likePost(int i) {
        // ponerle una restriccion para que solo pueda dar 1 like
        try {
            posts.get(i).likePost();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("El índice proporcionado es inválido.");
        } catch (NullPointerException e) {
            System.out.println("La lista de posts no ha sido inicializada.");
        }

    }

    public void dislikePost(int i) {
        // tmb una restriccion
        try {
            posts.get(i).likes--;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("El índice proporcionado es inválido.");
        } catch (NullPointerException e) {
            System.out.println("La lista de posts no ha sido inicializada.");
        }
    }

    public void pinPost(int i) {
        try {
            posts.get(i).setFav(true);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("El índice proporcionado es inválido.");
        } catch (NullPointerException e) {
            System.out.println("La lista de posts no ha sido inicializada.");
        }
    }
}
