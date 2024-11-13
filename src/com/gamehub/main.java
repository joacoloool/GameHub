package com.gamehub;
import com.gamehub.models.User;
import com.gamehub.utils.JsonUtil;
import com.gamehub.managers.*;



public class main {
    public static void main(String[] args) {

        //Testing

        Manager manager = new Manager();
       //com.gamehub.managers.Manager manager = com.gamehub.utils.JsonUtil.leerManager();

       if (manager.getUsers().isEmpty()) {
           User user = new User("Joacolool");
           User user2 = new User("Tom");
           manager.addUser(user);
           manager.addUser(user2);


           manager.getUsers().getFirst().getFeed().createPost("HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("1HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("12HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("123HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("1234HelloWorld");
           manager.getUsers().getFirst().getFeed().createPost("12345HelloWorld");
           manager.getUsers().getFirst().getFeed().togglePinPost(manager.getUsers().getFirst().getFeed().getPosts().get(5));
           manager.getUsers().getFirst().getFeed().togglePinPost(manager.getUsers().getFirst().getFeed().getPosts().get(0));

       }

      JsonUtil.guardar("manager.json", manager.toJson());

        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getGameLaunches());
    }
}

