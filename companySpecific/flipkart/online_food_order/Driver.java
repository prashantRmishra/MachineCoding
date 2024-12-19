package companySpecific.flipkart.online_food_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import companySpecific.flipkart.online_food_order.model.Item;
import companySpecific.flipkart.online_food_order.model.ItemOrder;
import companySpecific.flipkart.online_food_order.model.Menu;
import companySpecific.flipkart.online_food_order.model.OnlineUser;
import companySpecific.flipkart.online_food_order.model.Order;
import companySpecific.flipkart.online_food_order.model.Restaurant;
import companySpecific.flipkart.online_food_order.strategy.RestaurantPriceStrategy;

public class Driver {
    public static void main(String[] args) {
        Item item1 = new Item("king burger", 10);
        Item item2 = new Item("samosa pizza", 20);
        Item item3 = new Item("aloo pasta", 19);

        Item item4 = new Item("bhindi macroni", 12);
        Item item5 = new Item("samosa pizza", 25);
        Item item6 = new Item("aloo pasta", 17);

        Map<String,Item> items1 = new HashMap<>();
        items1.put(item1.getItemName(),item1);
        items1.put(item2.getItemName(),item2);
        items1.put(item3.getItemName(),item3);
        Map<String,Item> items2 = new HashMap<>();
        items2.put(item4.getItemName(),item4);
        items2.put(item5.getItemName(),item5);
        items2.put(item6.getItemName(),item6);

        Restaurant restaurant1 = new Restaurant(11123, "restaurant1", 15, new Menu(items1));
        Restaurant restaurant2 = new Restaurant(23223, "restaurant2", 12, new Menu(items2));
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant2);
        restaurants.add(restaurant1);
        OrderManagement orderManagement = new OrderManagement(restaurants, new RestaurantPriceStrategy());
        orderManagement.getRestaurantsDetails();
        orderManagement.printOrders();

        OnlineUser user1 =new OnlineUser("prashant");
        ItemOrder itemOrder1 = new ItemOrder("bhindi macroni", 3,user1);
        ItemOrder itemOrder2 = new ItemOrder("samosa pizza", 2,user1);
        List<ItemOrder> itemOrders = new ArrayList<>();
        itemOrders.add(itemOrder2);
        itemOrders.add(itemOrder1);
        List<Order> orders = orderManagement.createOrder(itemOrders);
        System.out.println(orders);
        orderManagement.getRestaurantsDetails();

        for(Order order : orders){
            orderManagement.orderDelivered(order);
        }
        
        orderManagement.getRestaurantsDetails();
    }
}
