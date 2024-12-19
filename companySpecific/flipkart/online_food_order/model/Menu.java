package companySpecific.flipkart.online_food_order.model;

import java.util.List;
import java.util.Map;

public class Menu {
    private Map<String,Item> items;
   
    @Override
    public String toString() {
        return "Menu [items=" + items + "]";
    }

    public Menu(Map<String,Item> items){
        this.items = items;
    }

    public Map<String,Item> getItems(){
        return this.items;
    }
    public synchronized void addItem(Item i){
        this.items.put(i.getItemName(),i);
    }
    public synchronized void removeItem(Item i){
        this.items.remove(i.getItemName());
    }
}
