package DiscountOnCoupon;

import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends DiscountDecorator {
    private Product p;
    private int percentage;
    List<ProductType> typesAllowed;
    public TypeCouponDecorator(Product p, int percentage, ProductType type){
        //discount are allowed only on below types of products
        typesAllowed = new ArrayList<>();
        typesAllowed.add(ProductType.electronics);
        typesAllowed.add(ProductType.fashion);
    }
    @Override
    public double getPrice() {
        double price = p.getPrice();
        if(typesAllowed.contains(p.getType())){
            return price - (price * percentage)/100;
        }
        return price;
    }
    
}
