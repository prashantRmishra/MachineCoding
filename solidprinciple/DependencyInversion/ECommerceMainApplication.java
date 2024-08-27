package solidprinciple.DependencyInversion;

public class ECommerceMainApplication {
    public static void main(String agrs[]) {
        ProductRepository productRepository = ProductFactory.create();
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        productCatalog.listAllProducts();
    }
}
