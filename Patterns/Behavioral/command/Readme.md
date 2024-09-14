Command pattern is one of the behavioral pattern, where a request is wrapped inside an object as **command** and that object is passed to an invoker, the invoker then looks for appropriate object that can handle this command and passes the command to the corresponding object, the object then executes the command.

This also follows OCP solid principle

Lets take an example of stock market, where `Stock` is a request that should be bought or sold(*command*), this `Stock` is wrapped under `Order`, this `Order` is then sent to broker (`Invoker`), broker then analyses the `Order` to decide whether this is `BuyOrder` or `SellOrder`  and finally Buy or sell order is performed(*execution of the command/request*)

`Stock` (request)
```java
public class Stock {
    private String name ;
    private int quantity;
    public Stock(String n, int q){
        this.name = n;
        this.quantity = q;
    }
    public void sell(){
        System.out.println("[Sell order of quantity "+ quantity +" for stock "+name +" has been performed]");
    }
    public void buy(){
        System.out.println("[Buy order of quantity "+ quantity +" for stock "+name +" has been performed]");
    }
}
```
`Order` (request wrapped inside an object as command)

```java
public interface Order {
    public void execute();
}
```


`Concrete Orders`

```java
public class BuyOrder implements Order {
    private Stock stock;
    public BuyOrder(Stock s){
        this.stock = s;
    }
    @Override
    public void execute(){
        stock.buy();
    }
}

public class SellOrder implements Order {
    private Stock stock;
    public SellOrder(Stock s){
        this.stock = s;
    }
    @Override
    public void execute(){
        stock.sell();
    }
}
```

`Broker`(Invoker that chooses appropriate Object that can handle the command/`Order`)

```java

import java.util.ArrayList;
import java.util.List;

public class Broker {
    List<Order> orders;
    public Broker(){
        orders = new ArrayList<>();
    }

    public void addOrder(Order e){
        orders.add(e);
    }

    public void placeOrder(){
        for(Order e : orders){
            e.execute();
        }
        orders.clear();// once all the orders are placed by the broker then , the list should be emptied
    }
}
```

`Main`

```java

public class Main {
    public static void main(String args[]){
        //requests
        Stock stock = new Stock("TCS",20);
        Stock stock2 = new Stock("Infy",10);
        //requests wrapped inside object(order) as commands
        Order order1 = new BuyOrder(stock);
        Order order2 = new SellOrder(stock2);

        //order is sent to broker 
        Broker broker = new Broker();
        broker.addOrder(order1);
        broker.addOrder(order2);

        //broker at runtime decides the appropriate Object for the reference Order
        //in other words the invokers decides which object is appropriate which can handle this command/Order
        broker.placeOrder();
    }
}
```

Output:

```output
[Buy order of quantity 20 for stock TCS has been performed]
[Sell order of quantity 10 for stock Infy has been performed]
```

**Key points for this pattern**
- Follows OCP,SIP solid principles.
- Type of order is known at runtime
