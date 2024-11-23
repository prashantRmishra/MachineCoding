package DiscountOnCoupon;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> products;
    public ShoppingCart(){
        products = new ArrayList<>();
    }
    public void addProductToCart(Product p){
        DiscountDecorator product = new TypeCouponDecorator(
            new PercentangeCouponDecorator(p, 10), 7, p.getType());
            products.add(product);
    }

    public double getPriceOfAllProductsAfterVariousDiscount(){
        double price = 0;
        for(Product p : products){
            price+=p.getPrice();
        }
        return price;
    }
    
}
