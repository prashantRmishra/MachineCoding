package companySpecific.flipkart.wellnessCenter.managers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.SessionSlot;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;
import companySpecific.flipkart.wellnessCenter.model.events.EventFactory;
import companySpecific.flipkart.wellnessCenter.model.events.EventType;
import companySpecific.flipkart.wellnessCenter.model.events.WellnessEvent;

public class EventManager {
    private Map<Integer,WellnessEvent> eventBookings = new HashMap<>();
    //add event
    public synchronized void addEvent(WellnessEvent event){
        eventBookings.put(event.getId(),event);
    }
    //remove event
    public synchronized void removeEvent(int id){
        WellnessEvent event = eventBookings.remove(id);
        event.getSlot().unbook();

    }
    //view all events
    public void viewAllAvailableEvents(){
        for(WellnessEvent event : eventBookings.values()){
            System.out.println(event);
        }
    }


    //book and event
    private synchronized WellnessEvent bookEvent(WellnessUser user,Doctor dr,String eventName,SessionSlot slot){
        WellnessEvent event =null;
        for(EventType e : EventType.values()){
            if(e.toString().equals(eventName)){
                event = EventFactory.createEvent(e);
                event.setDoctor(dr);
                event.setUser(user);
                event.setSlot(slot);
                slot.booked();
                eventBookings.put(event.getId(), event);
                break;
            }
        }
        
        return event;
    }

    public void searchEventByName(String name){
        for(WellnessEvent event : eventBookings.values().stream().filter(e-> (e.getEventName().contains(name) || e.getClass().toString().equals(name))).toList()){
            System.out.println(event);
        }
    }
    public void searchEventByDate(String date){
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for(WellnessEvent event : eventBookings.values().stream().filter(e-> e.getDate().equals(d)).toList()){
            System.out.println(event);
        }

    }
    public void searchEventByNameAndDate(String name, String date){
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for(WellnessEvent event : eventBookings.values().stream().filter(e-> e.getDate().equals(d)).filter(e-> (e.getEventName().contains(name) || e.getClass().toString().equals(name))).toList()){
            System.out.println(event);
        }

    }

    //book a session with dr on the given date for the give slots
    public WellnessEvent bookSessionWithDr(Doctor dr, WellnessUser user,String eventName, LocalTime startSlot){
        for( SessionSlot slot: dr.getDrAvailableSlots()){
            if(slot.getStartTime().equals(startSlot)){
                //make the booking
                bookEvent(user, dr,eventName,slot);
            }
        }
        return null; //booking not possible
    }
}
