package companySpecific.flipkart.model;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Slot {
    private LocalTime startTime;
    private LocalTime endTime;
    @Override
    public String toString() {
        return "Slot [" + startTime + "-" + endTime + "]";
    }
    private AtomicBoolean isBooked;
    public Slot(LocalTime s, LocalTime e){
        this.startTime = s;
        this.endTime =  e;
        isBooked = new AtomicBoolean(false);
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }
    public LocalTime getEndTime(){
        return this.endTime;
    }
    public void bookSlot(){
        this.isBooked.set(true);
    }
    public void freeSlot(){
        this.isBooked.set(false);
    }
    public boolean isBooked(){
        return this.isBooked.get();
    }
}
