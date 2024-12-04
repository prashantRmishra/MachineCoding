package MultiLevelCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LevelCache {
    private final int LEVEL;
    private final int capacity;
    private Map<String,String> map;
    public LevelCache(int level,int capacity){
        this.LEVEL = level;
        this.capacity = capacity;
        map = new LinkedHashMap<>(); // for ordered entry in the map
    }
    public int getLevel(){
        return this.LEVEL;
    }
    public String get(String key){
        String value = map.get(key);
        if(value ==null) return null;
        //put it back in the end to make it most recently used entry in the cache
        map.remove(key);
        map.put(key, value);
        return value;
    }
    public void put(String key,String value){
        if(map.size()==capacity){
            String k =  map.entrySet().iterator().next().getKey();
            map.remove(k);// remove the least recently used entry from the map(present at the front)
        }
        else if(map.containsKey(key)){
            map.remove(key);//remove it and put it back in the end making it most recently used value
        }
        map.put(key, value);//add new entry in them cache
    }
}
