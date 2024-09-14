package Patterns.Behavioral.command;

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
