package distributedCache;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class DistributedCache<K,V> {
    Map<Integer,Map<K,ValueStore<V>>> caches;
    Map<Integer,DelayQueue<ExpiryKey<K>>> distributedExpiryKeys;
  
    int serverCount;
    public DistributedCache(int noOfServers){
        this.serverCount = noOfServers;
        caches = new ConcurrentHashMap<>();
        distributedExpiryKeys = new ConcurrentHashMap<>();
        for(int i = 0;i<noOfServers;i++){
            caches.put(i, new ConcurrentHashMap<>());
            distributedExpiryKeys.put(i, new DelayQueue<>());
        }


        for(int i =0;i<serverCount;i++){
            final int serverId = i;
            Thread thread = new Thread(() -> {
                try {
                    removeExpiredKeys(serverId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void put(K key, V value, int timeInSeconds){
        long expiryTime = System.currentTimeMillis() + timeInSeconds * 1000;
        int serverNo  = getServer(key);
        caches.get(serverNo).put(key, new ValueStore<V>(value, expiryTime));
        distributedExpiryKeys.get(serverNo).add(new ExpiryKey<K>(key, expiryTime));

    }
    public V get(K k){
        int serverNo = getServer(k);
        ValueStore<V>  valueStore = caches.get(serverNo).getOrDefault(k, null);
        if(valueStore ==null || valueStore.isExpired()){
            //if the value is expired then the thead will remove it eventually
            return null;
        }
        return valueStore.value;
    }

    public void removeKey(K k){
        int serverNo = getServer(k);
        if(!caches.get(serverNo).containsKey(k)) return;
        caches.get(serverNo).remove(k);
    }
    private int getServer(K k){
        return Math.abs((k.hashCode() % serverCount));
    }

    public void removeExpiredKeys(int serverNo) throws InterruptedException{
        DelayQueue<ExpiryKey<K>> queue = distributedExpiryKeys.get(serverNo);
        Map<K,ValueStore<V>> cache = caches.get(serverNo);
        // remove expired keys in a loop
        while(true){
            ExpiryKey<K> expiredKey = queue.take();// block the thread until a key is expired and given
            ValueStore<V> valueStore = cache.get(expiredKey.k);
            if(valueStore!=null && valueStore.expiryTime == expiredKey.expiryTime){
                System.out.println("Server " + serverNo + " expired key: " + expiredKey.k);
                cache.remove(expiredKey.k);
            }
        }

    }
}
class ValueStore<V>{
    V value;
    long expiryTime;
    public ValueStore(V v, long expiryTime){
        this.value = v;
        this.expiryTime = expiryTime;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() > expiryTime;
    }
}
class ExpiryKey<K> implements Delayed{
    K k;
    long expiryTime;
    public ExpiryKey(K k, long expiryTime){
        this. k = k;
        this.expiryTime = expiryTime;
    }
    @Override
    public int compareTo(Delayed o) {
        ExpiryKey<?> ob = (ExpiryKey<?>) o;
        return Long.compare(this.expiryTime, ob.expiryTime);
    }
    // this is to get remaining time before the key expires
    @Override
    public long getDelay(TimeUnit unit) {
        long currentTime  = System.currentTimeMillis();
        long timeRemainingBeforeThisKeyExpires = expiryTime - currentTime;
        //return timeRemainingBeforeThisKeyExpires;
        //or we can also return 
        return unit.convert(timeRemainingBeforeThisKeyExpires, TimeUnit.MILLISECONDS);
    }

}
