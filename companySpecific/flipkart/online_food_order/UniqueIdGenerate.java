package companySpecific.flipkart.online_food_order;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerate {
    private static AtomicInteger id = new AtomicInteger(1);

    public static int getId() {
        return id.getAndIncrement();
    }

}
