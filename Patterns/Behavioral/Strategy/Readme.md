In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under **behavior pattern**.
In Strategy pattern, we create objects which represent various strategies and a context object whose behavior varies as per its strategy object.
Basically strategy pattern is nothing but OCP (Open-close Principle) i.e one of the solid principles which states that the object should be open for extension but closed for modification.

Read more about [OCP](https://dev.to/prashantrmishra/open-close-principle-a7h)

Consider an example of Mathematical Operations as strategies.
We will try to use these strategies in different context.

`Strategy`

```java
public interface Strategy {
    public int doOperation(int a, int b);
}
```

`Concrete Strategies`

```java
public class AdditionStrategy implements Strategy {
    @Override
    public int doOperation(int a, int b){
        System.out.println("performing + operation");
        return a+b;
    }
}

public class MultiplyStrategy implements Strategy {
    @Override
    public int doOperation(int a, int b){
        System.out.println("performing x operation");
        return a*b;
    }
}


public class SubtractStrategy implements Strategy {
    @Override
    public int doOperation(int a, int b){
        System.out.println("performing - operation");
        return a-b;
    }
}
```

`Context`: which will used to initialize the strategy

```java
public class Context {
    private Strategy strategy;
    public Context(Strategy s){
        this.strategy = s;
    }
    public void executeStrategy(int a, int b){
        System.out.println(this.strategy.doOperation(a, b));
    }
}
```
`Main`

```java
public class Main {
    public static void main(String args[]){
        Context context = new Context(new AdditionStrategy());
        int a = 9, b = 6;
        System.out.println("Add=>"+a+","+b+": "+ context.executeStrategy(a, b));
        Context context2 = new Context(new SubtractStrategy());
        System.out.println("Subtract=>"+a+","+b+": "+ context2.executeStrategy(a, b));
        Context context3 = new Context(new MultiplyStrategy());
        System.out.println("Multiply=>"+a+","+b+": "+ context3.executeStrategy(a, b));
    }
}
```

```output
Add=>9,6: 15
Subtract=>9,6: 3
Multiply=>9,6: 54
```

**Key takeaways**

- The Strategy pattern allows us to add more strategies in the future like divide, modulo or such Mathematical family of operations.
- This strictly follows the **OCP**(Open-Close principle)
- The reference variable `Strategy` is updated at run time, thus updating the behavior of the Strategy algorithm ( add, sub, multiply,etc)


