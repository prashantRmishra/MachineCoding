package companySpecific.flipkart.flipmed.model;


import java.util.concurrent.atomic.AtomicBoolean;

import companySpecific.flipkart.flipmed.Utility;

public class Appointment {
    private int id;
    private Slot slot;
    private Patient patient;
    private Doctor doctor;
    private AtomicBoolean isWaitlist;


    @Override
    public String toString() {
        return "\n[slot=" + slot + ",doc=" + doctor.getName()+"]\n";
    }

    public Appointment(Slot s, Patient p, Doctor d){
        this.patient = p;
        this.doctor = d;
        this.slot  =s;
        this.id = Utility.getUniqueId();
        isWaitlist = new AtomicBoolean(false);
    }

    public int getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setWaitlist(){
        this.isWaitlist.set(true);
    }
    public void freeWaitList(){
        this.isWaitlist.set(false);
    }
    public boolean isWaitlist(){
        return this.isWaitlist.get();
    }
}
