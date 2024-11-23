package DiscountOnCoupon;
public class ShoppingApp {
    public static void main(String args[]){
        Product p1 = new Item1(1, "Sofa", ProductType.furniture, 1000);
        Product p2 = new Item2(2 , "t-shirt", ProductType.fashion, 800);
        ShoppingCart cart = new ShoppingCart();
        cart.addProductToCart(p1);
        cart.addProductToCart(p2); 
        System.out.println(cart.getPriceOfAllProductsAfterVariousDiscount());
    }
}
