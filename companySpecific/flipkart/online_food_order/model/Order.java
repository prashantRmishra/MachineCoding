package companySpecific.flipkart.online_food_order.model;

import companySpecific.flipkart.online_food_order.UniqueIdGenerate;

public class Order {
    private int orderId;
    private OnlineUser user;
    private Restaurant restaurant;
    private Item item;
    private int quantity;
    private double price;
    private boolean isDelivered;
    private Status status;
    
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", user=" + user.getUserName() + ", restaurant=" + restaurant.getName() + ", item=" + item.getItemName()
                + ", quantity=" + quantity + ", price=" + quantity*price + ", isDelivered=" + isDelivered + ", status=" + status
                + "]";
    }
    public Order(OnlineUser user, Restaurant restaurant, Item item, int quantity, double price, boolean isDelivered) {
        this.user = user;
        this.restaurant = restaurant;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.isDelivered = isDelivered;
        this.orderId = UniqueIdGenerate.getId();
        this.status =status.in_progress;
    }
    public int getOrderId() {
        return orderId;
    }
    public OnlineUser getUser() {
        return user;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public Item getItem() {
        return item;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public boolean isDelivered() {
        return isDelivered;
    }
    public void setDelivered(){
        this.isDelivered = true;
    }
    public void updateStatus(Status status){
        this.status = status;
    }

}
