package DiscountOnCoupon;

public class Item1 extends Product {
    
    private double price;
    public Item1(int id, String name, ProductType type, double price){
        super(id, name, type);
        this.price = price;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
    
}
