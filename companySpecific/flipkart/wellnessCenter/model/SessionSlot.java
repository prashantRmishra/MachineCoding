package companySpecific.flipkart.wellnessCenter.model;

import java.time.LocalTime;

import companySpecific.flipkart.wellnessCenter.model.events.WellnessEvent;

public class SessionSlot {
    public LocalTime startTime;
    public LocalTime endTime;
    public boolean isFree;
    public WellnessEvent event;
    @Override
    public String toString() {
        return "SessionSlot [startTime=" + startTime + ", endTime=" + endTime + ", isFree=" + isFree + "]";
    }
    public SessionSlot(LocalTime s, LocalTime e){
        this.startTime = s;
        this.endTime = e;
        isFree =true;
    }
    public LocalTime getStartTime(){return this.startTime;}
    public LocalTime getEndTime(){return this.endTime;}
    public boolean isFree(){return this.isFree;}
    public void setEvent(WellnessEvent event){
        this.event = event;
    }
    public void removeEventId(){
        this.event = null;
    }
    public void booked(){this.isFree = false;}
    public void unbook(){this.isFree = true;}
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o.getClass()!=this.getClass()) return false;
        if(o == this) return true;
        SessionSlot s = (SessionSlot) o;
        if(s.startTime.equals(this.startTime) && this.endTime.equals(this.endTime)) return true;
        return false;

    }
}
