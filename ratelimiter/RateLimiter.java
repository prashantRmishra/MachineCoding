package ratelimiter;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RateLimiter {
    private int maxRequest;
    private long windowSize;
    //for the given user most recent request is added at the end of the queue and oldest is removed from the front of the queue
    //queue 
    private Map<String,Deque<Long>> userRequests = new ConcurrentHashMap<>();

    public RateLimiter(int maxRequest, long windowSizeInSeconds){
        this.maxRequest = maxRequest;
        this.windowSize = windowSizeInSeconds*1000;//in millis
    }

    public boolean isAllowed(String userId){
        long currentTime = System.currentTimeMillis();
        userRequests.putIfAbsent(userId, new ConcurrentLinkedDeque<>());
        Deque<Long> deque = userRequests.get(userId);

        //remove the requests time older that the window size
        synchronized(this){
            while(!deque.isEmpty() && (currentTime -  deque.peekFirst()) > windowSize){
                deque.removeFirst();
            }
        }
        //allowed
        if(deque.size() < maxRequest){
            deque.addLast(currentTime);
            return true;
        }
        ///not allowed
        return false;
    }
}
