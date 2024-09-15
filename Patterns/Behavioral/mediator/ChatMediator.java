package Patterns.Behavioral.mediator;

public interface ChatMediator {
    public void sendMessage(User user, String message);
    public void addUser(User user);
}
