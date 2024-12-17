package revision.parkinglot.factory;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueNumberGenerator {
    private static AtomicInteger id = new AtomicInteger(0);
    public static int getUniqueId(){
        return id.incrementAndGet();
    }
}
