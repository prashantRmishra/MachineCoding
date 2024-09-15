One of the behavioral design patterns, allowing objects to communicate with each other with the help of a mediator.
the mediator **promotes loose coupling** by preventing objects from having direct dependencies on each other. Instead, they use mediators to communicate with each other.

Let's understand this by implementing chatroom where different users chat with each other

`Mediator`

```java
public interface ChatMediator {
    public void sendMessage(User user, String message);
    public void addUser(User user);
}

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

```

`User`

```java
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
```

`Main`

```java
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
```
Output:

```output
prashant:sent:Hi All
ajay:received:Hi All
sandeep:received:Hi All
sandeep:sent:Hi Prashant
ajay:received:Hi Prashant
prashant:received:Hi Prashant
ajay:sent:Hi...
sandeep:received:Hi...
prashant:received:Hi...
```

**Key points**
- User objects are **loosely coupled**, i.e even if some users are deleted or modified it will not affect any other user
- **Mediator is the middle man** between all users




