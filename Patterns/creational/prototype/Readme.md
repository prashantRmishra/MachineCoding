It is one of the creational design patterns.
Used to create duplicate/shallow copies of the given object.
This pattern is useful when the creation of objects directly is costly example: If an object is created after querying a large database, then creating the object again and again is not economical in terms of performance.
Hence, once the object is created we cache the object and upon need of the same object in the future, we get it from the cache instead of creating it again from the database, and update the database as and when needed to reduce the database calls.


Note: We will have to use `Cloneable` i.e. a marker interface for the object that needs to be cloned, it(`Clonable`) does not contain any methods, it signals that a class can be cloned, which means creating a copy of an object.

*`Object.clone()` method* creates shallow Copy by Default
By default, the `clone()` method performs a shallow copy of the object. This means that it creates a new object and copies all the fields from the original object to the new object. However, if the object contains references to other objects (e.g., arrays, lists, or custom objects), the references themselves are copied, not the actual objects they point to.
As a result, both the original and the cloned object will reference the same objects for those fields. Any changes made to the referenced objects through one instance will reflect in the other.

Let's understand this with an example of a `Shape` object that can be cloned.

`Shape`

```java
public class Shape implements Cloneable {
    private String id;
    protected String shape;
    
    @Override
    public String toString() {
        return "Shape [id=" + id + ", shape=" + shape + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getShape() {
        return shape;
    }

    @Override
    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
```
`Child classes`

```java
public class Rectangle extends Shape {

    public Rectangle(){
        shape = "Rectangle";
    }
    public void draw(){
       System.out.println("calling draw() of Rectangle shape");
    }
}

public class Circle extends Shape {
    public Circle(){
        shape = "Circle";
    }
    public void draw(){
        System.out.println("Calling draw in Circle method");
    }
}
```

`Cache`

```java
public class ShapeCache {
    public static HashMap<String,Shape> cache = new HashMap<>();

    public static Shape cloneObject(String id){
        return (Shape)cache.get(id);
    }
    public static void addShapeInCache(Shape shape){
        cache.put(shape.getId(),shape);
    }
    
}
```

`Main`

```java
public class Main {
    public static void main(String args[]){
        Shape circle = new Circle();
        circle.setId("1");
        Shape rectangle = new Rectangle();
        rectangle.setId("2");
        
        ShapeCache.addShapeInCache(rectangle);
        ShapeCache.addShapeInCache(circle);

        Shape copyShape1 = (Shape) ShapeCache.cache.get(circle.getId());
        Shape copyShape2 =(Shape) ShapeCache.cache.get(rectangle.getId());

        System.out.println(copyShape1);
        System.out.println(copyShape2);
    }

}
```


Output:

```output
Shape [id=1, shape=Circle]
Shape [id=2, shape=Rectangle]
```    

**Key points**

- Both Circle and Rectangle follow the Liskov Substitution principle(SOLID principle) which states that **Object should be replaceable with their subtypes without affecting the correctness of the code**.
- Only a shallow copy of the `Shape` object is created.

