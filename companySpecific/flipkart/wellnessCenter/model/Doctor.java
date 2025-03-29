package companySpecific.flipkart.wellnessCenter.model;

import java.util.ArrayList;
import java.util.List;

import companySpecific.flipkart.wellnessCenter.model.events.WellnessEvent;
import companySpecific.flipkart.wellnessCenter.utility.EventUtility;

public class Doctor {
    private String drName;
    public String email;
    @Override
    public String toString() {
        return "Doctor [drName=" + drName + ", email=" + email + ", slots=" + slots + "]";
    }
    public List<SessionSlot> slots;
    public List<WellnessEvent> drEvents;
    public Doctor(String drName, String email){
        this.drName = drName;
        this.email = email;
        slots = EventUtility.getSlots();
        drEvents = new ArrayList<>();
        
    }
    public String getDrName(){return this.drName;}
    public String getEmail(){return this.email;}
    public List<SessionSlot> getSlots(){
        return this.slots;
    }

    public List<SessionSlot> getFreeSlot(){
        return this.slots.stream().filter(s-> s.isFree()).toList();
    }
    public List<SessionSlot> getBookedSlots(){
        return this.slots.stream().filter(s-> !s.isFree()).toList();
    }
    public boolean isDrAvailable(){
        return this.slots.size() > this.slots.stream().filter(s->!s.isFree()).count();
    }
    public List<SessionSlot> getDrAvailableSlots(){
        return this.slots.stream().filter(s-> s.isFree()).toList();
    }

}
