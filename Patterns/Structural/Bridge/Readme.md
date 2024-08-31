The Bridge design pattern is a **structural** pattern that **decouples an abstraction from its implementation so that the two can vary independently**.
The idea is to separate the abstraction from its implementation, allowing them to evolve independently

**Example**: 

Consider an example where you have a toy factory, you manufacture toys like `Train` and `Car` of different colors like red and green, now if you start creating toys of each type and of each color, you will end up creating a lot of toys beforehand.
Instead what you want to do is, you want to create toys based on sales analytics like which color toys are selling the most, in that way you can produce more toys of that kind, which will increase sales as well.
For this to happen there should be independence between the creation and coloring of the toys these two can not be done together, hence there has to be some kind of `Bridge` between them.
Depending on the sales, I want to increase the production of certain toys of certain colors.


Taking on the same example:
Let's assume there is an abstract class `Toy` and interface  `PaintAPI`
**We can add `PainAPI` as an attribute in `Toy` (this will act as a bridge to color the toys `Car` and `Train` independency as and when we create them and color them)**

_Class Diagram_: 

![Bridge pattern](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/gq31y305kdcenvtypxsy.png)

- Toys like Train and Car represent the Abstraction.
- Colors like Red and Green represent the Implementation.

**Key Points**:

*Independence*: By keeping the creation of toys (like Train and Car) independent from the coloring process (like Red and Green), you achieve the flexibility needed to respond to sales data.

*Bridge*: The Bridge is the mechanism that connects these two independent processes. You can decide to produce more Red Cars or Green Trains based on sales analytics without creating separate classes or objects for each combination upfront.

**Flexibility**:

This setup allows you to scale production dynamically:
If Red Cars sell well, you increase production by combining the existing Car design with the Red color.
If market trends shift, and Green Trains become popular, you adjust production similarly.
In software terms, this means your Car and Train classes don’t need to know or care about specific colors. They just rely on the coloring process, which can be swapped or adjusted based on demand. This separation is what the Bridge pattern is all about—keeping the abstraction (toys) and implementation (coloring) separate but connected in a flexible way


`PaintAPI`

```java
public interface PaintAPI {
    public void paintToy();
}
```

`Toy`

```java
public abstract class Toy {
    PaintAPI paintAPI;
    protected Toy(PaintAPI paintApi){
        this.paintAPI = paintApi;
    }
    abstract void createToy();
}
```

`Car`

```java
public class Car extends Toy {
    //dependency PaintAPI injection
    protected Car(PaintAPI paintApi) {
        super(paintApi);
    }

    @Override
    void createToy() {
        System.out.print("Car created and ");
        paintAPI.paintToy();
    }
}
```
`Train`

```java
public class Train extends Toy {
    //dependency PaintAPI injection
    protected Train(PaintAPI paintApi) {
        super(paintApi);
    }

    @Override
    void createToy() {
        System.err.print("Train created and ");
        paintAPI.paintToy();
    }
    
}
```

`PaintGreen`

```java
public class GreenPaint implements PaintAPI {
    @Override
    public void paintToy() {
        System.out.println("painted green");
    }
    
}
```
`PaintRed`

```java
public class RedPaint implements PaintAPI {

    @Override
    public void paintToy() {
        System.out.println("painted red");
    } 
}
```

`Main.java`

```java
public class ToyManufacturingMain {
    public static void main(String args[]){
        Toy toy1 = new Train(new GreenPaint());
        Toy toy2 = new Car(new RedPaint());
        toy1.createToy();
        toy2.createToy();
    }
}
```

```mathematica
Train created and painted green
Car created and painted red
```

Overall this example code follows all solid principles


