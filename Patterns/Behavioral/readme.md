# Behavioral design pattern



## Observer
The Observer pattern is a behavioral design pattern where an object (subject) notifies a list of dependent objects (observers) of any state changes, ensuring they stay synchronized

```java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String contentName);
}

class YoutubeChannel {
    private List<Observer> observers;
    private List<String> contents;
    private String name;

    public YoutubeChannel(String name) {
        this.observers = new ArrayList<>();
        this.contents = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void addContent(String newContent) {
        contents.add(newContent);
        notifyObservers(newContent);
    }

    private void notifyObservers(String contentName) {
        for (Observer observer : observers) {
            observer.update(contentName);
        }
    }
}

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String contentName) {
        System.out.println(name + " notified of new content: " + contentName);
    }
}

public class Main {
    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel("MrBeast");

        Subscriber subscriber1 = new Subscriber("Subscriber 1");
        Subscriber subscriber2 = new Subscriber("Subscriber 2");

        channel.addObserver(subscriber1);
        channel.addObserver(subscriber2);

        channel.addContent("Surviving on an empty island");
        channel.addContent("Spending 1 week inside a coffin");
    }
}

```
## Strategy

The Strategy pattern is a behavioral design pattern that enables selecting an algorithm's behavior at runtime by encapsulating it within interchangeable strategy objects

```java
// Strategy Interface
interface OperationStrategy {
    int doOperation(int a, int b);
}

// Concrete Strategies
class AdditionStrategy implements OperationStrategy {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}

class MultiplyStrategy implements OperationStrategy {
    @Override
    public int doOperation(int a, int b) {
        return a * b;
    }
}

class DivideStrategy implements OperationStrategy {
    @Override
    public int doOperation(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero error");
        }
        return a / b;
    }
}

// Context Class
class Context {
    private OperationStrategy strategy;

    public Context(OperationStrategy defaultOperationStrategy) {
        this.strategy = defaultOperationStrategy;
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeOperation(int a, int b) {
        System.out.println(strategy.doOperation(a, b));
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        OperationStrategy additionStrategy = new AdditionStrategy();
        OperationStrategy multiplyStrategy = new MultiplyStrategy();
        OperationStrategy divideStrategy = new DivideStrategy();

        Context context = new Context(additionStrategy); // Default strategy is addition
        context.executeOperation(2, 4); // Output: 6

        context.setStrategy(multiplyStrategy);
        context.executeOperation(2, 4); // Output: 8

        context.setStrategy(divideStrategy);
        try {
            context.executeOperation(2, 0); // Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage()); // Output: Divide by zero error
        }

        context.executeOperation(8, 4); // Output: 2
    }
}
```
## Command

The Command pattern is a behavioral design pattern that encapsulates a request as an object, allowing you to parameterize, queue, or log requests, and support undoable operations

```java
/*
A request object is wrapped under a command object which is passed to an invoker class which at run time sends the command to an object that can handle it, and the appropriate object handles or executes the
command object 
Example stock (request) > wrapped inside a command (Order : buy/sell) > passed to invoker(Broker)> which then sends the command appropriate obj like SellCommand or BuyCommand to execute the command
*/
// Command Interface
interface Order {
    void execute();
}

// Concrete Commands
class BuyOrder implements Order {
    private Stock stock;

    public BuyOrder(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}

class SellOrder implements Order {
    private Stock stock;

    public SellOrder(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}

// Receiver
class Stock {
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void buy() {
        System.out.println("Buy order placed for stock " + name + " at price " + price);
    }

    public void sell() {
        System.out.println("Sell order placed for stock " + name + " at price " + price);
    }
}

// Invoker
class Broker {
    private List<Order> orders;

    public Broker() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void executeOrders() {
        for (Order order : orders) {
            order.execute();
        }
        orders.clear(); // Clear all orders after execution
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Stock stock1 = new Stock("TCS", 2000);
        Stock stock2 = new Stock("HCL", 3000);

        Broker broker = new Broker();
        broker.addOrder(new BuyOrder(stock1));
        broker.addOrder(new SellOrder(stock2));

        // Execute all orders
        broker.executeOrders();
    }
}

```
## Chain of Responsibility

The Chain of Responsibility pattern is a behavioral design pattern that passes a request along a chain of handlers, allowing each handler to process or pass it to the next, promoting decoupling between sender and receiver

```java
// Logger Interface
interface Logger {
    void log(String msg, int level);
}

// Abstract Base Logger
abstract class AbstractLogger implements Logger {
    protected int level;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    public void log(String msg, int level) {
        if (this.level >= level) {
            write(msg);
        } else if (nextLogger != null) {
            nextLogger.log(msg, level);
        }
    }

    protected abstract void write(String msg);
}

// Concrete Loggers
class FatalLogger extends AbstractLogger {
    public FatalLogger() {
        this.level = 1;
    }

    @Override
    protected void write(String msg) {
        System.out.println("FatalLogger: " + msg);
    }
}

class ErrorLogger extends AbstractLogger {
    public ErrorLogger() {
        this.level = 2;
    }

    @Override
    protected void write(String msg) {
        System.out.println("ErrorLogger: " + msg);
    }
}

class InfoLogger extends AbstractLogger {
    public InfoLogger() {
        this.level = 3;
    }

    @Override
    protected void write(String msg) {
        System.out.println("InfoLogger: " + msg);
    }
}

class DebugLogger extends AbstractLogger {
    public DebugLogger() {
        this.level = 4;
    }

    @Override
    protected void write(String msg) {
        System.out.println("DebugLogger: " + msg);
    }
}

class GeneralLogger extends AbstractLogger {
    public GeneralLogger() {
        this.level = Integer.MAX_VALUE; // Catch-all logger
    }

    @Override
    protected void write(String msg) {
        System.out.println("GeneralLogger: " + msg);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        // Create loggers
        Logger fatalLogger = new FatalLogger();
        Logger errorLogger = new ErrorLogger();
        Logger infoLogger = new InfoLogger();
        Logger debugLogger = new DebugLogger();
        Logger generalLogger = new GeneralLogger();

        // Build the chain
        fatalLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(generalLogger);

        // Test the chain
        fatalLogger.log("This is a fatal message", 1);
        fatalLogger.log("This is an error message", 2);
        fatalLogger.log("This is an info message", 3);
        fatalLogger.log("This is a debug message", 4);
        fatalLogger.log("This is some other log message", 5);
    }
}
```

## Memento
The Memento pattern captures and restores an object's state without violating encapsulation, enabling undo or rollback functionality

```java
package Patterns.Behavioral;


import java.util.ArrayList;
import java.util.List;

class Subject{
    private String state;
    public Subject(String s){
        this.state = s;
    }

    public String getState(){
        return this.state;
    }
    public void updateState(String s){
        this.state = s;
    }
    public Memento saveStateToMemento(){
        return new Memento(state);
    }
    public void updateStateFromMemento(Memento m){
        this.state = m.getState();
    }
}
class Memento{
    private final String state;
    public Memento(String s){
        this.state = s;
    }
    public String getState(){
        return this.state;
    }
}
class SnapshotManager{
    private List<Memento> mementos;
    public SnapshotManager(){
        this.mementos = new ArrayList<>();
    }

    public void save(Memento m){
        mementos.add(m);
    }

    public Memento getMemento(int index){
        try {
            return mementos.get(index);
        } catch (Exception e) {
           System.out.println("given snapshot index does not exist");
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject("state 1");
        SnapshotManager manager = new SnapshotManager();
        System.out.println("current state is "+ subject.getState());//state 1
        subject.updateState("state 2");
        manager.save(subject.saveStateToMemento());//state 2 of subject is saved

        subject.updateState("state 3");
        System.out.println("current state is "+subject.getState()); //state 3

        subject.updateStateFromMemento(manager.getMemento(0));// state updated to state 2
        System.out.println("current state is "+ subject.getState());



    }
}
```

## State

```java
//State pattern is about an object changing its behavior as it transitions between different states in its lifecycle
//think of the different states of the Thread in its lifecycle
//Strategy pattern is about choosing between different behaviors (algorithms) at runtime
//think of various mathematical operations

class Context{
    private State state;
    public Context(){}
    public State getState(){
        return this.state;
    }
    public void setState(State state){
        this.state = state;
    }
}
abstract class State{
    protected Context context;
    public State(Context context){
        this.context = context;
    }
    abstract public void doAction();
    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
class StartState extends State{
    public StartState(Context context){
        super(context);
    }
    @Override
    public void doAction(){
        System.out.println("System is starting...");
        context.setState(this);
    }
}

class EndState extends State{
    public EndState(Context context){
        super(context);
    }
    @Override
    public void doAction(){
        System.out.println("System has ended.");
        context.setState(this);
    }
}

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        State startState = new StartState(context);
        startState.doAction();
        System.out.println(context.getState());
        State endState = new EndState(context);
        endState.doAction();
        System.out.println(context.getState());
    }
}

/*
System is starting...
StartState
System has ended.
EndState
*/
```
## template


The Template Method pattern defines the framework of an algorithm in a base class, allowing subclasses to provide specific implementations for certain steps while preserving the overall structure

```java

//template is a behavioral design pattern that defines the templates for method execution
//the methods should be executed in the same order as specified by the template, the methods can be
//overridden as per the use case
abstract class Template {
    public abstract void initGame();
    public abstract void startGame();
    public abstract void endGame();

    public final void play() { // final is important to insure this can not be overridden and used as is
        initGame();
        startGame();
        endGame();
    }
}

class CricketTemplate extends Template {
    @Override
    public void initGame() {
        System.out.println("Cricket is Initialized");
    }
    @Override
    public void startGame() {
        System.out.println("Cricket game has been started");
    }
    @Override
    public void endGame() {
        System.out.println("Cricket game has ended");
    }
}

class FootballTemplate extends Template {
    @Override
    public void initGame() {
        System.out.println("Football is Initialized");
    }
    @Override
    public void startGame() {
        System.out.println("Football game has been started");
    }
    @Override
    public void endGame() {
        System.out.println("Football game has ended");
    }
}

public class Main {
    public static void main(String[] args) {
        Template cricket = new CricketTemplate();
        cricket.play();

        System.out.println();

        Template football = new FootballTemplate();
        football.play();
    }
}

```

## Mediator
The Mediator pattern centralizes communication between multiple objects, promoting loose coupling by having objects interact through a mediator rather than directly with each other

```java

//Mediator promotes loose coupling, it insures that if two classes communicate with each other they don't have to have a hard dependency on each other
//this is achieved by using a third object that facilitate the two other objects to communicate with each other

import java.util.ArrayList;
import java.util.List;

abstract class User{

    protected String name;
    protected ChatRoom room;

    public User(ChatRoom room,String name){
        this.room = room;
        this.name = name;
        room.addUser(this);
    }

    abstract public void send(String m);
    abstract public void receive(String m);
}

class MobileAppUser extends User{

    public MobileAppUser(ChatRoom c, String name){
        super(c,name);
    }
    @Override
    public void send(String m){
        System.out.println(this.name +" sent: "+ m);
        room.send(this, m);
    }
    @Override
    public void receive(String m){
        System.out.println(name+" received: "+m);
    }
}

class ChatRoom{
    List<User> users;
    public ChatRoom(){
        users = new ArrayList<>();
    }
    public void addUser(User u){
        users.add(u);
    }
    public void removeUser(User u){
        users.remove(u);
    }
    public void send(User u,String m){
        for(User user : users){
            if(user!=u){
                user.receive(m);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();
        User user1  = new MobileAppUser(room, "prashant");
        User user2 = new MobileAppUser(room, "sandeep");
        User user3 = new MobileAppUser(room, "ajay");
        user1.send("Hi Ajay and Sandeep");
        user2.send("Hi Prashant");
        user3.send("Hi prashant");
    }
}

/*
 *output:
 *  
prashant sent: Hi Ajay and Sandeep
sandeep received: Hi Ajay and Sandeep
ajay received: Hi Ajay and Sandeep
sandeep sent: Hi Prashant
prashant received: Hi Prashant
ajay received: Hi Prashant
ajay sent: Hi prashant
prashant received: Hi prashant
sandeep received: Hi prashant

*/

```

## Difference between Strategy pattern and State pattern 

| Feature               | **Strategy Pattern**                                   | **State Pattern**                                   |
|-----------------------|--------------------------------------------------------|-----------------------------------------------------|
| **Intent**            | Select and change between different behaviors.         | Change behavior based on internal state transitions. |
| **Behavior Change**   | Explicitly chosen by the context.                      | Automatically changes when the object’s state changes.|
| **State Transitions** | Not concerned with transitions, just selecting strategies. | Manages internal state transitions.                 |
| **Example**           | Payment system with multiple payment methods.          | Document with different states like Draft, Approved. |
| **Key Focus**         | Encapsulate algorithms.                               | Encapsulate states and transitions.                 |

In summary, the Strategy pattern is about choosing between different behaviors (algorithms) at runtime, while the State pattern is about an object changing its behavior as it transitions between different states in its lifecycle.
