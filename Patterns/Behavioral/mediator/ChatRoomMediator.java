package Patterns.Behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomMediator implements ChatMediator {
    private List<User> users;
    public ChatRoomMediator(){
        this.users = new ArrayList<>();
    }
    @Override
    public void addUser(User u){
        users.add(u);
    }

    @Override
    public void sendMessage(User u, String message){
        for(User user : users){
            if(user!=u){
                user.receive(message);
            }
        }
    }
}
