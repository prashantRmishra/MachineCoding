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
## State
## template
## Mediator
