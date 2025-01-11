package googledocs;

import java.util.concurrent.atomic.AtomicInteger;

public class DocUniqueNumberGenerator {
    private static AtomicInteger id = new AtomicInteger(0);
    public static int getId(){
        return id.incrementAndGet();
    }
}
