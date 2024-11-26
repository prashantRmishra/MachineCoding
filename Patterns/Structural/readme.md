# Structural Design Patterns
## Adapter
The Adapter design pattern is used to allow incompatible interfaces to work together by providing a wrapper that converts one interface to another.

```java
//enum
enum Type{
    mp3,mp4,vlc;
}
//video players
abstract class VideoPlayer{
    public abstract void play(String fileName,Type type);
}
public class Mp4VideoPlayer extends VideoPlayer{
    @Override
    public void play(String fileName,Type type){
        //play mp4 video file
        System.out.println("Playing Mp4 file " + fileName);
    }
}
public class VLCMediaPlayer extends VideoPlayer{
    @Override
    public void play(String fileName, Type type){
        //play vlc media file
        System.out.println("Playing VLC file " + fileName);
    }
}

//adapter
class PlayerAdapter extends VideoPlayer{
    VideoPlayer player;
    public PlayerAdapter(Type type){
        if(type == Type.vlc) player = new VLCMediaPlayer();
        else if(type == Type.mp4) player = new Mp4VideoPlayer();
        else {
            throw new IllegalArgumentException("Unsupported video type: " + type);
        }
    }
    @Override
    public void play(String fileName, Type type){
        player.play(fileName,type);
    }
}
//music players
abstract class MusicPlayer{
    protected PlayerAdapter adapter;
    public abstract void play(String fileName, Type type);
}
public class Mp3MusicPlayer extends MusicPlayer{
    @Override
    public void play(String fileName,Type type){
        //plays music 
        if(type ==Type.mp3){
            System.out.println("Playing mp3 file " + fileName);
        }
        else{
            adapter = new PlayerAdapter(type);
            adapter.play(fileName,type);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new Mp3MusicPlayer();

        musicPlayer.play("song.mp3", Type.mp3);  // Playing MP3 file
        musicPlayer.play("movie.mp4", Type.mp4); // Playing MP4 file
        musicPlayer.play("clip.vlc", Type.vlc);  // Playing VLC file
        musicPlayer.play("unknown.xyz", Type.valueOf("xyz"));  // Unsupported media type
    }
}


```
## Bridge
The Bridge Design Pattern decouples an abstraction from its implementation, allowing both to vary independently

```java
//Bridge 
// Abstract Toy class
abstract class Toy {
    protected PaintApi paint;

    public Toy(PaintApi paint) {
        this.paint = paint;
    }

    abstract void create();
}

// Concrete Toy classes
class Train extends Toy {
    public Train(PaintApi paint) {
        super(paint);
    }

    @Override
    public void create() {
        System.out.println("Creating a Train...");
        paint.paint();
    }
}

class Car extends Toy {
    public Car(PaintApi paint) {
        super(paint);
    }

    @Override
    public void create() {
        System.out.println("Creating a Car...");
        paint.paint();
    }
}

// PaintApi interface
interface PaintApi {
    void paint();
}

// Concrete PaintApi implementations
class PaintBlue implements PaintApi {
    @Override
    public void paint() {
        System.out.println("Painting the toy blue.");
    }
}

class PaintGreen implements PaintApi {
    @Override
    public void paint() {
        System.out.println("Painting the toy green.");
    }
}

// Main class
public class Main {
    public static void main(String args[]) {
        Toy t1 = new Train(new PaintBlue());
        Toy t2 = new Car(new PaintGreen());

        t1.create();
        t2.create();
    }
}
```
Decoupling Abstraction and Implementation:
The Toy class represents the abstraction, and the PaintApi implementations (PaintBlue and PaintGreen) handle the implementation details. This achieves the pattern's goal of separating abstraction from implementation.

## Composite

The Composite Design Pattern allows you to treat individual objects and compositions of objects uniformly by organizing them into tree structures to represent part-whole hierarchies
```java
//Composite design pattern
import java.util.ArrayList;
import java.util.List;
enum Department{
    SALES,AWM,CIB;
}
class Employee{
    private String id;
    private String name;
    private Department department;
    private List<Employee> subOrdinates;
    public Employee(String id, String name, Department department){
        this.id = id;
        this.name = name;
        this.department = department;
        subOrdinates = new ArrayList<>();
    }
    public void addEmployee(Employee e){
        subOrdinates.add(e);
    }
    public void removeEmployee(Employee e){
        subOrdinates.remove(e);
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", subOrdinates=" + subOrdinates
                + "]";
    }

    
    
}
// Main class
public class Main {
    public static void main(String args[]) {
        Employee e1 = new Employee("1", "Prashant", Department.SALES);
        Employee e2 = new Employee("2", "sandeep", Department.SALES);
        Employee e3 = new Employee("3", "rahul", Department.SALES);
        Employee e4 = new Employee("4", "ajay", Department.SALES);
        Employee e5 = new Employee("5", "mira", Department.SALES);
        e1.addEmployee(e2);
        e1.addEmployee(e3);
        e4.addEmployee(e5);
        System.out.println(e1.toString());
        System.out.println(e2.toString());

    }
}
```

## Decorator

The Decorator Pattern allows you to add new functionality to an object dynamically without altering its structure, by wrapping it with additional behavior
```java
abstract class Toy {
    abstract void create();
}

class Train extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Train");
    }
}

class Car extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Car");
    }
}

abstract class ToyDecorator extends Toy {
    protected Toy toy;
    public ToyDecorator(Toy toy) {
        this.toy = toy;
    }
}

class BlueToyDecorator extends ToyDecorator {
    public BlueToyDecorator(Toy toy) {
        super(toy);
    }

    @Override
    public void create() {
        toy.create();
        paintBlue();
    }

    private void paintBlue() {
        System.out.println("Painting the toy blue");
    }
}

class RedToyDecorator extends ToyDecorator {
    public RedToyDecorator(Toy toy) {
        super(toy);
    }

    @Override
    public void create() {
        toy.create();
        paintRed();
    }

    private void paintRed() {
        System.out.println("Painting the toy red");
    }
}

public class Main {
    public static void main(String[] args) {
        Toy toy1 = new BlueToyDecorator(new Train());
        Toy toy2 = new RedToyDecorator(new Car());

        toy1.create();
        toy2.create();
    }
}

```
## Facade

The Facade Pattern provides a simplified interface to a complex system, hiding its complexities from the client

```java
import java.util.ArrayList;
import java.util.List;

// Toy abstract class
abstract class Toy {
    abstract void create();
}

// Concrete Toy classes
class Train extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Train");
    }
}

class Car extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Car");
    }
}

class Bicycle extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Bicycle");
    }
}

// Facade class
class ToyFacade {
    private List<Toy> toys;

    public ToyFacade() {
        toys = new ArrayList<>();
        // Initialize with existing toys, you can add more toys easily without modifying the facade class
        toys.add(new Train());
        toys.add(new Car());
        toys.add(new Bicycle());
    }

    public void createAllToys() {
        for (Toy toy : toys) {
            toy.create();
        }
    }

    public void createToy(Class<? extends Toy> toyClass) {
        for (Toy toy : toys) {
            if (toy.getClass().equals(toyClass)) {
                toy.create();
                return;
            }
        }
        System.out.println("Toy not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Facade instance to handle all toys
        ToyFacade facade = new ToyFacade();
        
        // Create all toys
        facade.createAllToys();
        
        // Create a specific toy
        facade.createToy(Train.class);
        facade.createToy(Car.class);
        facade.createToy(Bicycle.class);
    }
}
```
### Difference between facade and abstraction

| Aspect               | **Facade Pattern**                                      | **Abstraction**                                           |
|----------------------|---------------------------------------------------------|-----------------------------------------------------------|
| **Purpose**           | Simplifies interaction with a complex subsystem        | Hides details of an object or process, exposing only necessary functionality |
| **Focus**             | Provides a simple interface to complex subsystems      | Hides implementation details of classes or methods         |
| **Implementation**    | Typically involves a single class (Facade) that interacts with multiple subsystems | Involves abstract classes or interfaces to define common behavior |
| **Usage Scenario**    | Use when there are multiple interacting subsystems and you want to simplify their usage | Use when you want to define common behavior without specifying implementation |
| **Complexity**        | Simplifies multiple classes or systems into a unified interface | Simplifies individual objects, not necessarily multiple classes |


**Conclusion**:
Facade hides the complexity of interacting with a subsystem (often composed of several classes or components), providing a unified interface to the user.
Abstraction hides the internal details of a single class or object, providing a higher-level interface that defines what should be done without dictating how it is done.

## Proxy

## Flyweight

The Flyweight design pattern is used to reduce memory usage by sharing objects that have the same intrinsic state, while allowing for external state to vary


```java
import java.util.HashMap;
import java.util.Map;

abstract class Flyweight {
    protected char c;
    public Flyweight(char c) {
        this.c = c;
    }
    public char getChar() {
        return this.c;
    }
    public void render(int x, int y) {
        System.out.println("Rendering character '" + c + "' at coordinates (" + x + ", " + y + ")");
    }
}

class CharObject extends Flyweight {
    public CharObject(char c) {
        super(c);
    }
}

class CharacterCache {
    public static Map<Character, Flyweight> map = new HashMap<>();

    public static Flyweight getChar(char c) {
        Flyweight charObject = map.get(c);
        if (charObject == null) {
            charObject = new CharObject(c);
            addCharToChache(charObject);  // Add the new object to the cache
        }
        return charObject;
    }

    public static void addCharToChache(Flyweight obj) {
        map.put(obj.getChar(), obj);
    }
}

public class Main {
    public static void main(String[] args) {
        Flyweight charObj1 = CharacterCache.getChar('a');
        charObj1.render(12, 32);  // extrinsic property
        Flyweight charObj2 = CharacterCache.getChar('a');  // same as previous one
        charObj2.render(54, 65);
        
        // Checking if both references point to the same object (Flyweight reuse)
        System.out.println(charObj1 == charObj2);  // Should print 'true'
    }
}
```





































































































# Behavioral 
Strategy
Observer
command
template
chain of responsibility : like logging
mediator
memento
state

