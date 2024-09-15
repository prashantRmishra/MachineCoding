package Patterns.Behavioral.mediator;

public class Main {
    public static void main(String args[]){
        ChatMediator mediator = new ChatRoomMediator();
        User user1 = new User("prashant",mediator);
        User user2 = new User("sandeep", mediator);
        User user3 = new User("ajay", mediator);

        mediator.addUser(user3);
        mediator.addUser(user2);
        mediator.addUser(user1);

        user1.send("Hi All");
        user2.send("Hi Prashant");
        user3.send("Hi...");
    }
}
