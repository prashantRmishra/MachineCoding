package companySpecific.flipkart.flipmed;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
    private static AtomicInteger count = new AtomicInteger(0);

    public static int getUniqueId(){
        return count.incrementAndGet();
    }
    public static LocalTime getTime(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        // Parse the time string into a LocalTime object
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        return localTime;
    }
}
