package DiscountOnCoupon;

public abstract class Product {
    private int productId;
    private String productName;
    private ProductType type;
    public Product(){}
    public Product(int id, String name, ProductType type){
        this.productId = id;
        this.productName = name;
        this.type = type;
    }

    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }

    public ProductType getType() {
        return type;
    }

    public abstract double getPrice();
}
