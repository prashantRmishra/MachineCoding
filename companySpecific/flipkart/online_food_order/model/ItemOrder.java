package companySpecific.flipkart.online_food_order.model;

public class ItemOrder {
    private String itemName;
    private int quantity;
    private OnlineUser user;
    public ItemOrder(String i, int quantity,OnlineUser user){this.itemName = i; this.quantity = quantity;this.user = user;}
    public String getItemName(){return this.itemName;}
    public int getQuantity(){return this.quantity;}
    public OnlineUser getUser(){return this.user;}
}
