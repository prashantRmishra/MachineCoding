package DiscountOnCoupon;

public class PercentangeCouponDecorator extends DiscountDecorator {

    private Product p;
    private int percentage;

    public PercentangeCouponDecorator(Product p, int percentage){
        this.p = p;
        this.percentage = percentage;
    }

    //get updated price after discount
    @Override
    public double getPrice() {
        double price = p.getPrice();
        return price -(price*percentage)/100;
    }
    
}
