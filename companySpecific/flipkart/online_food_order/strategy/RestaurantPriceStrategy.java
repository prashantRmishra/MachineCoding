package companySpecific.flipkart.online_food_order.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.online_food_order.model.Item;
import companySpecific.flipkart.online_food_order.model.ItemOrder;
import companySpecific.flipkart.online_food_order.model.Restaurant;

public class RestaurantPriceStrategy implements Strategy {
    private Map<String,List<RestaurantItemPrice>> itemRestaurantMap = null;
    public RestaurantPriceStrategy(){
        itemRestaurantMap = new HashMap<>();
    }

    /**
     * this returns list of restaurants where each and every restaurants have all the items and we can choose the restaurant have least total price(this logic you will have to add in the OrderManagement)
     */
    /**
     * public List<Restaurant> getRestaurants(List<ItemOrder> itemOrders, List<Restaurant> restaurants){
        List<Restaurant> result = new ArrayList<>();
        for(Restaurant restaurant :restaurants){
            int itemsFound = 0;
            for(ItemOrder itemOrder : itemOrders){
                if(restaurant.getMenu().getItems().containsKey(itemOrder.getItemName())) itemsFound++;
            }
            if(itemsFound == itemOrders.size()){
                result.add(restaurant);// found restaurant which has all the items 
            }
        }
        return result;// in this result individual restaurants can server all the ItemOrder( as they have all the Items available with them)
    }
    */

    @Override
    public List<Restaurant> getRestaurants(List<ItemOrder> itemOrders, List<Restaurant> restaurants) {
        for(ItemOrder itemOrder : itemOrders){

            List<RestaurantItemPrice> results = new ArrayList<>();
           
            restaurants.stream().forEach(r-> {
                Map<String,Item> items = r.getMenu().getItems();
                if(items.containsKey(itemOrder.getItemName())){
                    Item item = items.get(itemOrder.getItemName());
                    results.add(new RestaurantItemPrice(r, item.getItemPrice()));
                }
            });
            Collections.sort(results,(a,b)-> Double.compare(a.getPrice(), b.getPrice()));
            itemRestaurantMap.put(itemOrder.getItemName() , results);
        }
        return getRestaurants(itemRestaurantMap);
    }
    public List<Restaurant> getRestaurants(Map<String, List<RestaurantItemPrice>> map){
        List<Restaurant> restaurants  = new ArrayList<>();
        for(Map.Entry<String,List<RestaurantItemPrice>> entry : map.entrySet()){
            for(RestaurantItemPrice restaurantItemPrice : entry.getValue()){
                restaurants.add(restaurantItemPrice.getRestaurant()) ;
            }
        }
        return restaurants;
    }
    
    static class RestaurantItemPrice{
        Restaurant restaurant;
        double price;
        public RestaurantItemPrice(Restaurant res, double price){this.price =price;this.restaurant =res;}
        public Restaurant getRestaurant(){
            return this.restaurant;
        }
        public double getPrice(){
            return price;
        }
    }
}
