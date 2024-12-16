package companySpecific.flipkart.flipmed.manager;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import companySpecific.flipkart.flipmed.Utility;
import companySpecific.flipkart.flipmed.model.Doctor;
import companySpecific.flipkart.flipmed.model.Patient;
import companySpecific.flipkart.flipmed.model.Slot;
import companySpecific.flipkart.flipmed.model.Speciality;
import companySpecific.flipkart.flipmed.strategy.EnquiryStrategy;



public class UserManager {
    private Set<Patient>patients;
    private Set<Doctor> doctors;
    private EnquiryStrategy strategy;
    public UserManager(EnquiryStrategy strategy){
        this.patients = new HashSet<>();
        this.doctors = new HashSet<>();
        this.strategy = strategy;
    }
    public synchronized boolean addPatient(Patient p){
        return this.patients.add(p);
       
    }
    public synchronized boolean addDoctor(Doctor p){
        return this.doctors.add(p);
    }
    public synchronized boolean markDocAvailability(Doctor d1, String slotRequest) {
        String slots[] = slotRequest.split(",");
        List<Slot> slotList = new ArrayList<>();
        for(String slot  :slots){
            String[] sd = slot.split("-");
            LocalTime start  = Utility.getTime(sd[0]);
            LocalTime end = Utility.getTime(sd[1]);
            if(ChronoUnit.MINUTES.between(start, end)>30) {
                return false;
            }
            slotList.add(new Slot(start, end)); 
        }
        d1.addSlots(slotList);
        return true;
        
        
    }
    public void showAvailabilityDoctors(Speciality speciality) {
       for(Doctor d : strategy.getDoctors(doctors, speciality.toString())){
        System.out.println(d);
       }
    }

    
}
