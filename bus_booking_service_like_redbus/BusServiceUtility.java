package bus_booking_service_like_redbus;

import java.util.concurrent.atomic.AtomicInteger;

public class BusServiceUtility {
    private static AtomicInteger id = new AtomicInteger(0);
    public static int getUniqueId() {
       return id.incrementAndGet();
    }
}
