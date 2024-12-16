package companySpecific.flipkart.model;


import java.util.concurrent.atomic.AtomicBoolean;

import companySpecific.flipkart.Utility;

public class Appointment {
    private int id;
    private Slot slot;
    private Patient patient;
    private Doctor doctor;
    private AtomicBoolean isWaitlist;


    @Override
    public String toString() {
        return "Appointment [id=" + id + ", slot=" + slot + ", patient=" + patient + ", doctor=" + doctor
                + ", isWaitlist=" + isWaitlist + "]";
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
    public boolean isWaitlist(){
        return this.isWaitlist.get();
    }
}
