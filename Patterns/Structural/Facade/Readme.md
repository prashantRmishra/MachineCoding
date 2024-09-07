Facade is the design pattern that hides the complexity of the system and provides a simple interface for the client to interact with the system.

We can understand with the same example of Toy making

![facade](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/86qrom3dl1ejzfcx0xaq.png)

interface Toy has concrete implementation by Train and Car, We can create ToyMaker(a facade class) that will interact with these Classes to make different toys, the user will interact with the simple custom methods of the ToyMaker class to create different toys and will be un-aware of what complexities are involved in making these toys behind the scene.

`Toy`
```java
public interface Toy {
    public void createToy();
}
```
`Train`
```java
public class Train implements Toy {
    @Override
    public void createToy(){
        System.out.println("Creating Train toy");
    }
}
```
`Car`
```java
public class Car implements Toy {
    @Override
    public void createToy(){
        System.out.println("Creating Car toy");
    }
}
```

`ToyMaker`

```java
public class ToyMaker {
    private Toy car;
    private Toy train;

    public ToyMaker(){
        car = new Car();
        train = new Train();
    }

    public  void createTrain(){
        train.createToy();
    }

    public void createCar(){
        car.createToy();
    }
}
```

`ToyManufacturingMain`

```java
public class ToyManufacturingMain {
    public static void main(String args[]){
        ToyMaker  toyMaker = new ToyMaker();
        

        //if client want to create train
        toyMaker.createTrain();
        //if client wants to create car
        toyMaker.createCar();
    }
}
```

Output:

```output
Creating Train toy
Creating Car toy
```


