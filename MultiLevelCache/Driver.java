package MultiLevelCache;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        //assuming we have 3 level of cache
        Cache cache1 = new LevelCache(1, 2, 2 , 2);
        Cache cache2 = new LevelCache(2, 3, 3 , 3);
        Cache cache3 = new LevelCache(3, 4, 4 , 4);
        //initialize cache manager with 3 level of caches
        CacheManager manager = new CacheManager(List.of(cache1,cache2,cache3));

        manager.write("key1", "value1"); 
        manager.write("key2", "value2"); 
        manager.read("key1");
        manager.read("key3");
        manager.write("key3","value3");
        manager.getStats();

    }
}
