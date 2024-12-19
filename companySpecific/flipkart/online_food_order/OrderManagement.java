package companySpecific.flipkart.online_food_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.online_food_order.model.Item;
import companySpecific.flipkart.online_food_order.model.ItemOrder;
import companySpecific.flipkart.online_food_order.model.Order;
import companySpecific.flipkart.online_food_order.model.Restaurant;
import companySpecific.flipkart.online_food_order.model.Status;
import companySpecific.flipkart.online_food_order.strategy.Strategy;

public class OrderManagement {
    private List<Restaurant> restaurants;
    private Map<Integer,Order> orders;

    public void printOrders() {
        for(Map.Entry<Integer,Order> entry: orders.entrySet()){
            System.out.println(entry.getValue()+"\n");
        }
    }

    private Strategy strategy;

    public OrderManagement(List<Restaurant> restaurants,Strategy strategy){
        this.restaurants = restaurants;
        orders = new HashMap<>();
        this.strategy = strategy;
    }

    //create order
        //find the restaurant using strategy
        //place order

    public List<Order> createOrder(List<ItemOrder> itemOrders){
        List<Order> orders = new ArrayList<>();
      
        List<Restaurant> restaurants = getRestaurantsByStrategy(itemOrders);// this needs to be updated from the strategy
        
        if(restaurants.isEmpty()) return null; //order can not be placed as Restaurant can not be found for all the ordered Item
        //check if restaurant has capacity
        for(Restaurant restaurant : restaurants){
            if(restaurant.getItemProcessingCapacity()==0) return null;// one of the restaurant is out of capacity for taking orders,try after some time
        }

        //create Order
        for(ItemOrder itemOrder : itemOrders){
            for(Restaurant restaurant : restaurants){
                //check if the restaurant has capacity
                if(restaurant.getItemProcessingCapacity()<= itemOrder.getQuantity()) continue;
                Order order = placeOrder(itemOrder,restaurant);
                if(order!=null) {
                    orders.add(order);
                    break;// once the ItemOrder placed, place the next ItemOrder
                }
            }
        }
        // if all the orders are placed then orders size will be equal to itemOrders
        return orders.size()< itemOrders.size() ? null : orders;
    }
    public Order placeOrder(ItemOrder itemOrder, Restaurant restaurant){
        Order order = null;

        Map<String, Item> items = restaurant.getMenu().getItems();
        if(items.containsKey(itemOrder.getItemName())){
            Item item = items.get(itemOrder.getItemName());
            order = new Order(itemOrder.getUser(), restaurant, item, itemOrder.getQuantity(), item.getItemPrice(), false);
        }
        if(order!=null){
            restaurant.decrementItemProcessingCapacity(itemOrder.getQuantity());
            orders.put(order.getOrderId(), order);
        }
        return order;
    }

    public List<Restaurant> getRestaurantsByStrategy(List<ItemOrder> itemOrders){
        return strategy.getRestaurants(itemOrders, restaurants);
    }
    //update order
    public void orderDelivered(Order order){
        order.setDelivered();
        order.updateStatus(Status.delivered);
        //update the capacity back of the restaurant
        order.getRestaurant().updateItemProcessingCapacity(order.getQuantity());
    }
    //cancel order
    public void cancelOrder(Order order){
        orders.get(order.getOrderId());
        order.setDelivered();
        order.updateStatus(Status.cancelled);
        order.getRestaurant().updateItemProcessingCapacity(order.getQuantity());
    }
    //add restaurant
    public void addRestaurant(Restaurant r){
        this.restaurants.add(r);
    }
    //remove restaurant
    public void removeRestaurant(Restaurant r){
        this.restaurants.remove(r);
    }

    public void getRestaurantsDetails() {
        for(Restaurant r :restaurants){
            System.out.println(r +" \n");
        }
    }
}
