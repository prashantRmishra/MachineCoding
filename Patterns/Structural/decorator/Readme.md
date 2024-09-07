Decorator pattern:
It is one of the structural design pattern.
This pattern acts as an **wrapper** to an existing class.
This pattern creates a wrapper class that wraps the existing class providing **additional functionality** keeping the class methods and signature intact.

Let's take the same example we took in Bridge design pattern for Toy and Paint

`Toy`

```java
public interface Toy {
    public void createToy();
}
```
Implementation of Toy `Car`

```java
public class Car implements Toy {
    @Override
    public void createToy(){
        System.out.print("Creating Car ");
    }
}
```
Implementation of Toy `Train`
```java
public class Train implements Toy {
    @Override
    public void createToy(){
        System.out.print("Creating Train ");
    }
}
```

`ToyDecorator`

```java
/**
 * 
 * Decorator is an abstract class not an interface because we want to initialize
 * the Object (in our case Train or Car) so that we can provide additional feature on 
 * top of the existing Object (in this case Car or Train) when we extend this abstract class
 * And initialization is possible through dependency injection via constructor
 * 
 * If we had interface in this place then we would not have been able to initialize 
 * Object in run time, rather we will have had to initialize the Object in the individual 
 * implementation of interface ToyDecorator which is not efficient
*/
public abstract class ToyDecorator implements Toy{
    protected Toy toy;
    public ToyDecorator(Toy t){
        this.toy = t;
    }
}
```
`GreenToyDecorator`

```java
public class GreenToyDecorator extends ToyDecorator {

    public GreenToyDecorator(Toy t){
        super(t);
    }

    @Override
    public void createToy() {
        toy.createToy();
        paint(toy.getClass().getSimpleName());
        
    }
    //additional feature
    public void paint(String toyName){
        System.out.print("and painted the toy "+toyName+" with green color\n");
    }
    
}
```
`RedToyDecorator`
```java
public class RedToyDecorator extends ToyDecorator {

    public RedToyDecorator(Toy t) {
        super(t);
    }

    @Override
    public void createToy() {
        toy.createToy();
        paint(toy.getClass().getSimpleName());
        
    }
    //additional feature
    public void paint(String toyName){
        System.out.print("and painted the toy "+toyName+" with red color\n");
    }
    
}
```

`ToyManufacturingMain`
```java
public class ToyManufacturingMain {
    public static void main(String args[]){
        ToyDecorator redToy = new RedToyDecorator(new Car());
        ToyDecorator greenToy = new GreenToyDecorator(new Train());
        redToy.createToy();
        greenToy.createToy();
    }
}
```
**Output**: 

```output
Creating Car and painted the toy Car with red color
Creating Train and painted the toy Train with green color
```

The above code follows all the SOLID principles




