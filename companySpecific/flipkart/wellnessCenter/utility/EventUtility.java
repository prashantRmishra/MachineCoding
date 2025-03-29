package companySpecific.flipkart.wellnessCenter.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import companySpecific.flipkart.wellnessCenter.model.SessionSlot;

public class EventUtility {
    private static AtomicInteger eventId  = new AtomicInteger();

    public static int getId(){
        return eventId.getAndIncrement();
    }

    public  static List<SessionSlot> getSlots(){
        List<SessionSlot> slot = new ArrayList<>();
        slot.add(new SessionSlot(LocalTime.parse("09:30", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("10:30", DateTimeFormatter.ofPattern("HH:mm"))));
        slot.add(new SessionSlot(LocalTime.parse("11:30", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("12:30", DateTimeFormatter.ofPattern("HH:mm"))));
        slot.add(new SessionSlot(LocalTime.parse("14:30", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("15:30", DateTimeFormatter.ofPattern("HH:mm"))));
        slot.add(new SessionSlot(LocalTime.parse("17:30", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("18:30", DateTimeFormatter.ofPattern("HH:mm"))));

        return slot;
    }
}
