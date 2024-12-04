package MultiLevelCache;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    List<Cache> levels;
    //to keep track of top k read and write operation time in all the levels of cache
    //this is useful in calculating the average time of read and write in the multilevel cache
    List<Integer> readTimes;
    List<Integer> writeTimes;

    public CacheManager(List<Cache> levels){
        this.levels =levels;
        readTimes = new LinkedList<>();
        writeTimes   = new LinkedList<>();
    }

    //methods that will be used in this class
    //find key
    // if the key is present in one of the level(currentLevel) then it must be updated in all the previous levels<=currentLevel of the cache
    public void read(String key){
        int totalTime = 0;
        String value = null;
        for(Cache cache : levels){
            totalTime+=cache.getReadTime();// add the time required to read this cache
           if(cache.get(key)!=null){
            //found in this cache 
            value = cache.get(key);
            // update this key and value in all the high priority caches(previous caches)
            for(int cacheNumber = cache.getLevel()-1;cacheNumber>=0;cacheNumber--){
                Cache c = levels.get(cacheNumber);
                c.put(key, value);
            }
            break;// break out once this is done
           }
        }
        if(value!=null){
            System.out.println("found: "+value+", total read time "+ totalTime);
        }
        else{
            System.out.println("key not found, total read time "+ totalTime);
        }
        addToHistory(readTimes, totalTime);
    }
    //add entry
    //using LRU
    public void write(String key, String value){
        int totalTime = 0;
        for(Cache c: levels){
            totalTime+=c.getReadTime(); // time to  read caches 
            if(c.get(key)!=null && value.equals(c.get(key))) break;// if the key,value is already present in the cache (any of the caches does not matter) no need to write again
            totalTime+=c.getWriteTime();
            c.put(key, value);// write in all the caches
        }
        System.out.println("total write time "+ totalTime);
        addToHistory(writeTimes, totalTime);
    }

    public void getStats(){
        // usage
        System.out.println("Usage:");
        for(Cache c : levels){
            System.out.println("C" + (c.getLevel())+ ": "+ (double)c.getUsage()/(double)c.getCapacity());
        }

        //average read/write
        System.out.println("Cache(s) Read/Write average:");
        System.out.println("Read:(average of last "+CacheConfig.AVERAGE+")"+getAverage(readTimes));
        System.out.println("Write:(average of last "+CacheConfig.AVERAGE+")"+getAverage(readTimes));

    }
    public void addToHistory(List<Integer> readTimes, int totalTime){
        if(readTimes.size()==CacheConfig.AVERAGE){
            readTimes.remove(0);//remove the oldest entry 
        }
        readTimes.add(totalTime);
    }
    public double getAverage(List<Integer> history){
        return history.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}
