package Patterns.Behavioral.mediator;

public class User {
    ChatMediator mediator;
    private String name;

    public User(String n,ChatMediator m){
        this.name = n;
        this.mediator = m;
    }
    public String getName(){
        return this.name;
    }

    public void send(String message){
        System.out.println(this.name+":sent:"+message);
        mediator.sendMessage(this,message);
    }
    public void receive(String message){
        System.out.println(this.name+":received:"+ message);
    }
}
