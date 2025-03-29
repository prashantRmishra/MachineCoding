package companySpecific.flipkart.wellnessCenter.model.events;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.SessionSlot;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;
import companySpecific.flipkart.wellnessCenter.utility.EventUtility;

public class WellnessEvent {
    private Doctor dr;
    private LocalDateTime date;
    private boolean isBooked;

    private WellnessUser user;
    private SessionSlot slot;
    private String eventName;
    private int id;

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return "WellnessEvent [dr=" + dr + ", date=" + date + ", isBooked=" + isBooked + ", user=" + user + ", slot="
                + slot + ", eventName=" + eventName + ", id=" + id + "]";
    }

    public WellnessEvent(Doctor dr, WellnessUser user, SessionSlot slot,String eventName, String dateString){
        this.dr = dr;
        this.user =user;
        this.slot = slot;
        this.eventName = eventName;
        id = EventUtility.getId();
        this.date = LocalDateTime.now();
       

    }
    public LocalDateTime getDate(){
        return this.date;
    }
    public void setBooked(){this.isBooked = true;}
    public void setSlot(SessionSlot slot){
        this.slot = slot;
    }
    public void setDoctor(Doctor dr){
        this.dr = dr;
    }
    public void unBook(){this.isBooked = false;}
    public boolean isBooked(){return this.isBooked;}
    public Doctor getDoc(){return this.dr;}
    public WellnessUser getUser(){return this.user;}
    public SessionSlot getSlot(){return this.slot;}
    public int getId(){ return this.id;}
    public void setUser(WellnessUser user){
        this.user = user;
    }

    
}
