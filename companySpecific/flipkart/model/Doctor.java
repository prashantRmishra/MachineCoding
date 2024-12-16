package companySpecific.flipkart.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import companySpecific.flipkart.Speciality;

public class Doctor extends User {
    Set<Slot> slots;
    @Override
    public String toString() {
        List<Slot> l = getAvailableSlots();
        return "Dr."+getName()+" [slots=" +(l.isEmpty() ? "No slots available":l) + "]";
    }
    private Speciality speciality;
    public Doctor(String name, UserType type,Speciality speciality){
        super(name,type);
        slots = new HashSet<>();
        this.speciality = speciality;
    }

    public Set<Slot> getSlots(){
        return this.slots;
    }
    public synchronized  void addSlots(List<Slot> slots){
        this.slots.addAll(slots);
    }
    public Speciality getSpeciality(){
        return this.speciality;
    }

    public List<Slot> getAvailableSlots(){
        return slots.stream().filter(slot-> !slot.isBooked()).toList();
    }

}
