package companySpecific.flipkart;

import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
    private static AtomicInteger count = new AtomicInteger(0);

    public static int getUniqueId(){
        return count.incrementAndGet();
    }
}
