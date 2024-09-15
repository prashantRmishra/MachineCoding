One of the structural patterns aims to reduce memory usage by sharing as much data as possible with similar objects.
It is particularly **useful when dealing with a large number of similar objects**, where creating a new instance for each object would be expensive in terms of memory consumption.
key concepts:
Intrinsic state: The state that is shared between multiple objects is independent of the context and remains the same across different objects.
Extrinsic state: The state that is unique to each object and is passed from the client. This state can vary and is not stored in the Flyweight object

Key participants:

Flyweight: Interface that the Flyweight object to receive the Extrinsic state and use it.
ConcreteFlyweight: Implements the Flyweight and stores the intrinsic state.
FlyweightFactory: Manages the Flyweight objects and ensures the sharing of interfaces, it returns an existing Flyweight if it already exists.

Client(like Main class): Maintains a reference to Flyweight and supplies Extrinsic state when it needs to interact with the Flyweight object.

Let's take an example of a Flyweight object of character
Suppose we have a text editor that needs to render a large amount of text. Each character can be represented as an object, but having a separate object for every character would waste a lot of memory. Instead, we can use Flyweights to share the character objects that represent each letter, and store the extrinsic state like the position or formatting outside

`Flyweight`
```java
public interface Flyweight {
    public void display(int x, int y);//x, y are the extrinsic state of the Flyweight object
}
```
`ConcreteFlyweight`
```java
public class CharacterFlyweight implements Flyweight {
    private char ch;
    public CharacterFlyweight(char c){
        this.ch  = c;
    }
    @Override
    public void display(int x ,int y){
        System.out.println("[drawing character: "+this.ch+" at co-ordinates:("+x+","+y+")]");
    }
    
}
```
`FlyweightFactory`
```java
public class FlyweightFactory {
    private static HashMap<Character,Flyweight> flyweights = new HashMap<>();
    public static Flyweight getFlyweight(char c){
        Flyweight flyweight = flyweights.getOrDefault(c,null);
        if(null==flyweight){
            flyweight = new CharacterFlyweight(c);
            flyweights.put(c,flyweight);
        }
        return flyweight;
    }
}
```

`Main`

```java
public class Main {
    public static void main(String args[]){
        Flyweight flyweight1 = FlyweightFactory.getFlyweight('a');
        Flyweight flyweight2 = FlyweightFactory.getFlyweight('b');
        Flyweight flyweight3 = FlyweightFactory.getFlyweight('a');// will use the same object that is referenced by flyweight1

        flyweight1.display(1, 2);//'a' displayed at 1,2
        flyweight2.display(3, 4);//'b' displayed at 3,4
        flyweight3.display(5, 7); // 'a'(shared) displayed at 5,7
    }
}

```

Output:

```output:
[drawing character: a at co-ordinates:(1,2)]
[drawing character: b at co-ordinates:(3,4)]
[drawing character: a at co-ordinates:(5,7)]
```

**Key Points**
- Memory Efficiency: Reduces memory usage by sharing objects, especially when the intrinsic state is large or there are many objects.
- Performance Improvement: Reducing the number of objects created, it can improve the performance of the application when managing large numbers of objects.

**Disadvantages**
*Complexity*: The pattern can add complexity to the code, especially in managing the extrinsic and intrinsic states separately.
*Overhead*: If there are few objects to share, the Flyweight pattern might introduce unnecessary complexity without significant memory savings.