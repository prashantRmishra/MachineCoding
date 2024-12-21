package companySpecific.flipkart.ride_sharing_app;

import java.util.concurrent.atomic.AtomicInteger;

public class RideSharingUtility {
    private static AtomicInteger id = new AtomicInteger(0);
    public static int getUniqueNumber() {
        return id.incrementAndGet();
    }

}
