Memento is one of the Behavioural Design patterns that allows an o**bject to save and restore its previous state** without exposing its internal structure.
This is useful when you want to provide a redo/undo feature in your application, another example will be restoring different commit versions in the repository in git.


**_Key participants in the memento pattern_**
Originator: The object whose state needs to be saved.
Memento: An object that acts as a Snapshot of the Originator's state.
CareTaker: The Object responsible for keeping the memento safe, but it does not modify or inspect the state of the memento object.

`Originator`

```java
public class Originator{
    private String state; // could be any data, string, Wrapper classes or custom objects as well
    public Originator(){}

    public void updateState(String s){
        this.state = s;
    }
    public String getCurrentState(){
        return this.state;
    }
    public Memento saveStateToMemento(){
        return new Memento(this.state);
    }
    public void getStateFromMemento(Memento memento){
        this.state = memento.getState();
    }
}
```

`Memento`
```java
public class Memento {
    private String state;
    public Memento(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}
```

`CareTake`
```java

public class CareTaker {
    private List<Memento> mementos;

    public CareTaker(){
        mementos = new ArrayList<>();
    }
    public void add(Memento memento){
        this.mementos.add(memento);
    }
    public Memento get(int index){
        return this.mementos.get(index);
    }
}
```

`Main`

```java
public class Main {
    public static void main(String args[]){
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.updateState("state #1");
        originator.updateState("state #2");
        //save current state in memento and store memento in caretake
        careTaker.add(originator.saveStateToMemento());// one state is saved

        originator.updateState("state #3");
        //save current state in memento and store memento in caretake
        careTaker.add(originator.saveStateToMemento());

        originator.updateState("state #4");
         //current state of originator
         System.out.println("current state : "+originator.getCurrentState());
        //first snapshot of state
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("first snapshot/saved state: "+ originator.getCurrentState());
        //second snapshot of state
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("second snapshot/saved state: "+originator.getCurrentState());

       
    }
}
```

Output: 

```output
current state: state #4
first snapshot/saved state: state #2
second snapshot/saved state: state #3
```

**Key points**
- Similar to the above state (String), any type of state can be wrapped in a memento object and saved in the caretaker list.
- Encapsulation of State: The pattern encapsulates the state and prevents clients from directly manipulating it.
- Undo Mechanism: It helps implement undo/redo operations without revealing the internal workings of the object.

**Drawbacks**
Memory Usage: Storing states can consume a lot of memory, especially if the object state is large or many states are saved.
Complexity: Managing multiple states can become complex if not designed carefully.


