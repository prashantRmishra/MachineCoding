package courseregistration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UniversityIdGenerator {
    private static AtomicInteger number = new AtomicInteger(1);
    Set<String> set = new HashSet<>();

    public static String get() {
        String id = String.valueOf(LocalDate.now().getYear()) + number.getAndIncrement();
        return id;
    }

}
