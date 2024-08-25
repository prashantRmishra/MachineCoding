package machinecodingexamples.solidprinciple.DependencyInversion;

/*
 * High Level module
*/
import java.util.List;

public class ProductCatalog {

    private ProductRepository productRepository;

    public ProductCatalog(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void listAllProducts(){
        List<String> allProductsNames = productRepository.getAllProductNames();
        //Display all products names
        allProductsNames.forEach(product-> System.out.println(product));
    }
}
