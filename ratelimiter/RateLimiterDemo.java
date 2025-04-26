package ratelimiter;

public class RateLimiterDemo {
    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(5, 10);

        for(int i =0;i<7;i++){
            boolean isAllowed = rateLimiter.isAllowed("prashant");
            System.out.println("Request "+ (i+1)+": "+(isAllowed? "allowed": "not allowed"));
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
    }
}
