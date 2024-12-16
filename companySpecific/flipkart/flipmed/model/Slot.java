package companySpecific.flipkart.flipmed.model;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Slot {
    private LocalTime startTime;
    private LocalTime endTime;
    private AtomicBoolean isBooked;
    @Override
    public String toString() {
        return "Slot [" + startTime + "-" + endTime + "]";
    }
    
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
