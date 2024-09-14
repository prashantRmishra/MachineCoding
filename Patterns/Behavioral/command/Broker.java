package Patterns.Behavioral.command;

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
