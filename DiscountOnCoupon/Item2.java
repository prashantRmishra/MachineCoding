package DiscountOnCoupon;

public class Item2 extends Product {
    private double price;
    public Item2(int id, String name, ProductType types, double price){
        super(id, name, types);
        this.price = price;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
    
}
