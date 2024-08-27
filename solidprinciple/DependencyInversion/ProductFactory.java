package machinecodingexamples.solidprinciple.DependencyInversion;

public class ProductFactory {
    
    public static SQLProductRepository create(){
        return new SQLProductRepository();
    }
}
