import I.SortTool;

import java.util.ArrayList;

public class Manager {

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
}
