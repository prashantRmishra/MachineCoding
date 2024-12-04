package MultiLevelCache;


import java.util.LinkedHashMap;
import java.util.Map;

public class LevelCache  implements Cache{
    private final int LEVEL;
    private final int capacity;
    private int readTime;
    private int writeTime;
    private Map<String,String> map;
    public LevelCache(int level,int capacity,int readTime, int writeTime){
        this.LEVEL = level;
        this.capacity = capacity;
        map = new LinkedHashMap<>(); // for ordered entry in the map
        this.readTime = readTime;
        this.writeTime = writeTime;
    }
    @Override
    public int getLevel(){
        return this.LEVEL;
    }
    @Override
    public  int getReadTime(){
        return this.readTime;
    }
    @Override
    public int getWriteTime(){
        return this.writeTime;
    }
    @Override
    public int getCapacity(){
        return this.capacity;
    }
    @Override
    public int getUsage(){
        return map.size();// usage is nothing but how much of the map size has been utilized compared to the original capacity
    }

    // simple LRU cache replacement policy has been employed with most recently used at the end and least recently used at the 
    //front of the doubly linked list
    @Override
    public String get(String key){
        String value = map.get(key);
        if(value ==null) return null;
        //put it back in the end to make it most recently used entry in the cache
        map.remove(key);
        map.put(key, value);
        return value;
    }
    @Override
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
