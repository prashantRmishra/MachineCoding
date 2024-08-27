package solidprinciple.DependencyInversion;
/*
 * Low level module 
*/
import java.util.Arrays;
import java.util.List;
public class SQLProductRepository  implements ProductRepository{
    @Override
    public List<String> getAllProductNames(){
        return Arrays.asList("soap","toothpaste");
    }
}
