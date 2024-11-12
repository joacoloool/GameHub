public class main {
    public static void main(String[] args) {

        //Testing

        Manager manager = new Manager();
       //Manager manager = JsonUtil.leerManager();

       if (manager.getUsers().isEmpty()) {
           User user = new User("Joacolool");
           User user2 = new User("Tom");
           manager.addUser(user);
           manager.addUser(user2);


           manager.getUsers().getFirst().feed.createPost("HelloWorld");
           manager.getUsers().getFirst().feed.createPost("1HelloWorld");
           manager.getUsers().getFirst().feed.createPost("12HelloWorld");
           manager.getUsers().getFirst().feed.createPost("123HelloWorld");
           manager.getUsers().getFirst().feed.createPost("1234HelloWorld");
           manager.getUsers().getFirst().feed.createPost("12345HelloWorld");
           manager.getUsers().getFirst().feed.togglePinPost(manager.getUsers().getFirst().feed.getPosts().get(5));
           manager.getUsers().getFirst().feed.togglePinPost(manager.getUsers().getFirst().feed.getPosts().get(0));

       }

      JsonUtil.guardar("manager.json", manager.toJson());

        // user.getGame(1).run();
        //user.getGame(2).run();
        // System.out.println(user.getGame(2).getLastTime());
        //System.out.println(user.getGame(2).getPlayCount());
    }
}

