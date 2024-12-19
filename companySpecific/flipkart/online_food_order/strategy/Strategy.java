package companySpecific.flipkart.online_food_order.strategy;

import java.util.List;

import companySpecific.flipkart.online_food_order.model.ItemOrder;
import companySpecific.flipkart.online_food_order.model.Restaurant;

public interface Strategy {
    public List<Restaurant> getRestaurants(List<ItemOrder> itemOrders, List<Restaurant> restaurants);
}
