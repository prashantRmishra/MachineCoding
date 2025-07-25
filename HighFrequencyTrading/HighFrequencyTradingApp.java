import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HighFrequencyTradingApp {
   public static void main(String[] args) {
    MarketDataHandler handler = new MarketDataHandler();
    TradingStrategy buyOnLowStrategy = new BuyOnLowStategy(new OrderManager(new RiskManager(20)), 10,110);
    TradingStrategy sellOnHighPrice = new SellHighStrategy(new OrderManager(new RiskManager(20)), 10,200);
    handler.addTradingStrategy(buyOnLowStrategy);
    handler.addTradingStrategy(sellOnHighPrice);
    handler.marketDataHandler();
   }
}

class MarketData{
    String symbol;
    double price;

    public MarketData(String s, double p){
        this.symbol = s;
        this.price = p;
    }

    @Override
    public String toString() {
        return "MarketData [symbol=" + symbol + ", price=" + price + "]";
    }
}
enum OrderStatus{
    new_order, partially_filled_order, filled_order,cancelled_order;
}
class Idgenerator{
    static AtomicInteger ordeId = new AtomicInteger(1);
}
class Order{
    String orderId;
    String symbol;
    double price;
    int qty;
    OrderStatus status;
    public Order(String symbol, double price, int qty) {
        this.symbol = symbol;
        this.price = price;
        this.qty = qty;
        this.status = OrderStatus.new_order;
        this.orderId = "order-"+symbol+"-"+Idgenerator.ordeId.getAndIncrement();
    }
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", symbol=" + symbol + ", qty=" + qty +"]";
    }
}

class RiskManager{
    private int thresholdQty;// max qty allowed for all the order placement strategies
    public RiskManager(int thresholdQty){
        this.thresholdQty = thresholdQty;
    }
    boolean isOrderAllowed(Order order){
        if(order.qty<=thresholdQty) return true;
        return false;
    }
}

class OrderManager{

    private Map<String,Order> orderBook = new ConcurrentHashMap<>();
    private RiskManager manager;
    public OrderManager(RiskManager manager){
        this.manager = manager;
    }
    public void placeOrder(Order order){
        if(!manager.isOrderAllowed(order)) {
            System.out.println(order+" failed, rejected by RiskManager");
            return;
        }
        // later this can be updated to partial_filled 
        order.status = OrderStatus.filled_order;
        orderBook.put(order.orderId, order);
        System.out.println(order+ " Order placed "+ order.orderId+"for symbol "+ order.symbol+" at price"+ order.price+" for qty "+ order.qty);
    }
    //update order
    //cancel order
    public void cancelOrder(String orderId){
        Order existingOrder = orderBook.getOrDefault(orderId, null);
        if(existingOrder ==null){
            System.out.println("Order with id "+ orderId+" does not exists!!");
        }
        else if(existingOrder.status.equals(OrderStatus.new_order)){
            existingOrder.status = OrderStatus.cancelled_order;
            orderBook.remove(existingOrder.orderId);
            System.out.println("Order with id "+ orderId +" cancelled successfully!!");
        }
        else{
            System.out.println("Order with id "+ orderId+"  can not be called ");
        }
    }
    public void modifyOrder(Order updatedOrder) {
        Order existing = orderBook.get(updatedOrder.orderId);
        if (existing != null && existing.status == OrderStatus.new_order) {
            if (manager.isOrderAllowed(updatedOrder)) {
                existing.price = updatedOrder.price;
                existing.qty = updatedOrder.qty;
                System.out.println("Order Modified: " + updatedOrder.orderId);
            } else {
                System.out.println(" Modified order rejected by RiskManager: " + updatedOrder.orderId);
            }
        } else {
            System.out.println("Cannot modify order: " + updatedOrder.orderId);
        }
    }
}

interface TradingStrategy{
    void onNewData(MarketData data);
}

class BuyOnLowStategy implements TradingStrategy{
    OrderManager orderManager;
    private int qtyToBuy;
    double buyPriceThreshold;
    public BuyOnLowStategy(OrderManager orderManager , int qtyToBuy, double buyPriceThreshold){
        this.orderManager = orderManager;
        this.qtyToBuy = qtyToBuy;
        this.buyPriceThreshold= buyPriceThreshold;
    }
    @Override
    public void onNewData(MarketData data) {

        if(data.price<=buyPriceThreshold){
            Order order = new Order(data.symbol, data.price, qtyToBuy);
            System.out.println(order +" created for "+ this.getClass().getName());
            orderManager.placeOrder(order);
        }
    }
}
class SellHighStrategy implements TradingStrategy {
    OrderManager orderManager;
    private int qtyToSell;
    double sellPriceThreshold;
    public SellHighStrategy(OrderManager orderManager , int qtytoSell, double sellPriceThreshold){
        this.orderManager = orderManager;
        this.sellPriceThreshold= sellPriceThreshold;
        this.qtyToSell = qtytoSell;
    }
    @Override
    public void onNewData(MarketData data) {
        if (data.price >=sellPriceThreshold) {
            Order order = new Order(data.symbol, data.price, -qtyToSell);
            System.out.println(order +" created for "+ this.getClass().getName());
            orderManager.placeOrder(order);
        }
    }
}

class MarketDataHandler {
    List<TradingStrategy> strategies;
    public MarketDataHandler(){
        this.strategies = new ArrayList<>();
    }
    public void addTradingStrategy(TradingStrategy strategy){
        strategies.add(strategy);
    }
    public void removeStrategy(TradingStrategy strategy){
        strategies.remove(strategy);
    }

    public void marketDataHandler(){

        new Thread(()-> {
            while(true){
                String [] symbols = {"APPL","INFY","TCS"};

                Random random = new Random();
                int index = random.nextInt(symbols.length);
                int price  = 50 + random.nextInt(251);
                MarketData data = new MarketData(symbols[index], price);
                System.out.println(data +" arrived");
                newDataNotification(data);
                try {
                    Thread.sleep(1000);// one data per second
                } catch (InterruptedException e) {
                    System.out.println("failed to process new market data");
                }
            }
        }).start();
    }

    public void newDataNotification(MarketData data){
        for(TradingStrategy strategy : strategies){
            strategy.onNewData(data);
        }
    }
}

/*
output sample: 
MarketData [symbol=TCS, price=266.0] arrived
Order [orderId=order-TCS-7, symbol=TCS, qty=-10] created for SellHighStrategy
Order [orderId=order-TCS-7, symbol=TCS, qty=-10] Order placed order-TCS-7for symbol TCS at price266.0 for qty -10
MarketData [symbol=TCS, price=213.0] arrived
Order [orderId=order-TCS-8, symbol=TCS, qty=-10] created for SellHighStrategy
Order [orderId=order-TCS-8, symbol=TCS, qty=-10] Order placed order-TCS-8for symbol TCS at price213.0 for qty -10
MarketData [symbol=APPL, price=76.0] arrived
Order [orderId=order-APPL-9, symbol=APPL, qty=10] created for BuyOnLowStategy
Order [orderId=order-APPL-9, symbol=APPL, qty=10] Order placed order-APPL-9for symbol APPL at price76.0 for qty 10
MarketData [symbol=INFY, price=94.0] arrived
Order [orderId=order-INFY-10, symbol=INFY, qty=10] created for BuyOnLowStategy
Order [orderId=order-INFY-10, symbol=INFY, qty=10] Order placed order-INFY-10for symbol INFY at price94.0 for qty 10
MarketData [symbol=APPL, price=53.0] arrived
Order [orderId=order-APPL-11, symbol=APPL, qty=10] created for BuyOnLowStategy
Order [orderId=order-APPL-11, symbol=APPL, qty=10] Order placed order-APPL-11for symbol APPL at price53.0 for qty 10
*/