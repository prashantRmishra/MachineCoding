package companySpecific.flipkart.online_food_order.model;

public class Item {
    private String itemName;
    private double itemPrice;
    @Override
    public String toString() {
        return "Item [itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
    }
    public Item(String name,double itemPrice){
        this.itemName = name;
        this.itemPrice = itemPrice;
    }
    public final String getItemName(){return this.itemName;}
    public final double getItemPrice(){return this.itemPrice;}
}
