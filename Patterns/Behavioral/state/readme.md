The State is one of the Behavioral design pattern, In this the behavior of a class changes based on the its state.

Let's understand this with an example:

Key concepts

Context: Class/object whose behavior changes based on state

State: abstract state
Concrete State: representing various states, which when changed the behavior of Context class also changes.


`State.java`

```java
public interface State {
    public void doAction(Context context);
}
```

`Concrete Implementation of State`

```java
public class StartState implements State {
    private Context context;
    public StartState(){}
    @Override
    public void doAction(Context context){
        this.context = context;
        this.context.setState(this);
        System.out.println("Player is in StartState");
    }

    public String toString(){
        return "Start State";
    }
}

public class EndState implements State {
    private Context context;
    public EndState(){}
    @Override
    public void doAction(Context context){
        this.context = context;
        this.context.setState(this);
        System.out.println("Player is in EndState");
    }

    public String toString(){
        return "End State";
    } 
}

```

`Main`

```java
public class Main {
    public static void main(String args[]){
        Context context = new Context();
        State state = new StartState();
        state.doAction(context);
        //current state
        System.out.println(context.getState().toString());

        State state2 = new EndState();
        state2.doAction(context);
        //new State
        System.out.println(context.getState().toString());
    }
}
```

output:

```output:
Player is in StartState
Start State
Player is in EndState
End State
```

note: The above code follows ISP,LSP,SRP,OCP of solid principles

