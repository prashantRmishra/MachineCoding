package companySpecific.flipkart.model;

import java.time.LocalTime;

import companySpecific.flipkart.Utility;

public class Appointment {
    private int id;
    private Slot slot;
    private String patientName;
    private String doctorName;


    public Appointment(Slot s, String p, String d){
        this.patientName = p;
        this.doctorName = d;
        this.slot  =s;
        this.id = Utility.getUniqueId();
    }

    public int getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
