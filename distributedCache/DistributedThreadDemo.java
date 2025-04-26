package distributedCache;

public class DistributedThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        DistributedCache<String, String> cache = new DistributedCache<>(3); // 3 servers

        System.out.println("Putting keys with TTL...");
        cache.put("apple", "red", 2);   // expires after 2 seconds
        cache.put("banana", "yellow", 5); // expires after 5 seconds
        cache.put("grape", "purple", 10); // expires after 10 seconds

        System.out.println("Immediately fetching keys:");
        System.out.println("apple -> " + cache.get("apple"));
        System.out.println("banana -> " + cache.get("banana"));
        System.out.println("grape -> " + cache.get("grape"));

        System.out.println("\nSleeping for 3 seconds...\n");
        Thread.sleep(3000);

        System.out.println("Fetching after 3 seconds:");
        System.out.println("apple -> " + cache.get("apple"));   // should be null (expired)
        System.out.println("banana -> " + cache.get("banana")); // should still exist
        System.out.println("grape -> " + cache.get("grape"));   // should still exist

        System.out.println("\nSleeping for 3 more seconds...\n");
        Thread.sleep(3000);

        System.out.println("Fetching after total 6 seconds:");
        System.out.println("banana -> " + cache.get("banana")); // should be null (expired)
        System.out.println("grape -> " + cache.get("grape"));   // should still exist

        System.out.println("\nSleeping for 5 more seconds...\n");
        Thread.sleep(5000);

        System.out.println("Fetching after total 11 seconds:");
        System.out.println("grape -> " + cache.get("grape"));   // should now be null (expired)
    }
}
