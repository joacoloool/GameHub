import I.ListHelper;
import I.SortTool;

import java.util.ArrayList;

public class Manager implements ListHelper<User>, SortTool<User> {

    protected ArrayList<User> users;

    //Constructors

    public Manager() {
        users = new ArrayList<>();
    }

    public Manager(ArrayList<User> users) {
        this.users = users;
    }

    //Getters and setters

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //Methods

    public void addUser(User user) {
        users.add(user);
    }

    public void modify() {
        //implement
    }


    @Override
    public void delete(User user) {

    }

    @Override
    public void add(User user) {

    }

    @Override
    public void sortType(User user) {

    }

    @Override
    public void sortFavorite(User user) {

    }

    @Override
    public void sortLastTime(User user) {

    }

    @Override
    public void sortPlayCount(User user) {

    }

    @Override
    public void groupGenre(User user) {

    }
}
