package companySpecific.flipkart.online_food_order.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private int restaurantId;
    private String name;
    private AtomicInteger maxItemProcessingCapacity;
    private Menu menu;

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", maxItemProcessingCapacity="
                + maxItemProcessingCapacity + ", menu=" + menu + "]";
    }
    public Restaurant(int id,String name,int maxCapacity, Menu menu){
        this.restaurantId = id;
        this.name = name;
        this.maxItemProcessingCapacity = new AtomicInteger(maxCapacity);
        this.menu = menu;
    }
    public Menu getMenu(){
        return this.menu;
    }
    public String getName(){return this.name;}
    public int getRestaurantId(){return this.restaurantId;}
    public int getItemProcessingCapacity(){
        return this.maxItemProcessingCapacity.get();
    }
    public void decrementItemProcessingCapacity(int by){
        this.maxItemProcessingCapacity.set(this.maxItemProcessingCapacity.get()-by);
    }
    public void updateItemProcessingCapacity(int by){
        this.maxItemProcessingCapacity.set(this.maxItemProcessingCapacity.get()+by);
    }
}
