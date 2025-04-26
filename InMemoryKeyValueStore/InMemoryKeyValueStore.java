package InMemoryKeyValueStore;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class InMemoryKeyValueStore<K,V> {
    private ConcurrentHashMap<K,ValueStore<V>> cache = new ConcurrentHashMap<>();
    private DelayQueue<ExpiringKey<K>> expiringKeys = new DelayQueue<>();

    public InMemoryKeyValueStore(){
        Thread thread = new Thread(this::removeExpiredKeys);
        thread.setDaemon(true);
        thread.start();;
    }
    //put
    public void put(K key, V value, int timeInSeconds){
        long expiryTime = System.currentTimeMillis() + timeInSeconds*1000; //this will expire after this time
        cache.put(key, new ValueStore<>(value, expiryTime));
        expiringKeys.add(new ExpiringKey<>(key, expiryTime));
    }
    //get
    public V get(K key){
        ValueStore<V> value = cache.get(key);
        if(value==null || value.isExpired()){
            return null;
        }
        return value.getValue();
    }
    //remove
    public void remove(K k){
        cache.remove(k);
    }

    public void removeExpiredKeys(){
        while(true){
            try {
                ExpiringKey<K> key = expiringKeys.take();//block until expiry time is reached
                ValueStore<V> valueStore = cache.get(key.getKey());
                if(valueStore!=null && valueStore.getExpiryTime() == key.getExpiryTime()){
                    cache.remove(key.getKey());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ValueStore<V>{
    private V value;
    private long expiryTime;
    public ValueStore(V v, long expiryTime){
        this.value = v;
        this.expiryTime = expiryTime;
    }
    public long getExpiryTime(){return this.expiryTime;}
    public boolean isExpired(){
        return System.currentTimeMillis() > expiryTime;
    }
    public V getValue(){return value;}
}

class ExpiringKey<K> implements Delayed{
    K k;
    long expiryTime;
    public ExpiringKey(K k, long t){
        this.k = k;
        this.expiryTime = t;
    }
    public long getExpiryTime(){return this.expiryTime;}
    public K getKey(){return this.k;}
    @Override
    public int compareTo(Delayed o) {
        ExpiringKey<?> other = (ExpiringKey<?>)o;
        return Long.compare(this.expiryTime, other.expiryTime);
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long time =  expiryTime - System.currentTimeMillis();
        return unit.convert(time, TimeUnit.MILLISECONDS);
    }
}
