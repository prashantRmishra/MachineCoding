package InMemoryKeyValueStore;

public class Demo {
    public static void main(String[] args) {
        InMemoryKeyValueStore inMemoryKeyValueStore = new InMemoryKeyValueStore<String,String>();

        inMemoryKeyValueStore.put("prashant", "prashant is super rich", 10);
        inMemoryKeyValueStore.put("sandeep", "sandeep is super rich as well", 3);
        System.out.println(inMemoryKeyValueStore.get("prashant")); // will print "prashant is super rich"
        
        try {
            Thread.sleep(4000);
            System.out.println(inMemoryKeyValueStore.get("sandeep")); //null, as this is expired
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}
