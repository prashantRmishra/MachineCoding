package companySpecific.flipkart.manager;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import companySpecific.flipkart.Speciality;
import companySpecific.flipkart.model.Appointment;
import companySpecific.flipkart.model.Doctor;
import companySpecific.flipkart.model.Patient;
import companySpecific.flipkart.model.Slot;
import companySpecific.flipkart.model.User;
import companySpecific.flipkart.strategy.EnquiryStrategy;



public class UserManager {
    private Set<Patient>patients;
    private Set<Doctor> doctors;
    private AppointmentManager manager;
    private EnquiryStrategy strategy;
    public UserManager(AppointmentManager manager,EnquiryStrategy strategy){
        this.patients = new HashSet<>();
        this.doctors = new HashSet<>();
        this.manager = manager;
        this.strategy = strategy;
    }
    public List<Appointment> getAppointments(User u){
        return manager.getUserAppointments(u);
    }
    public synchronized void addPatient(Patient p){
        this.patients.add(p);
        System.out.println(p.getName() +" registered successfully!!");
    }
    public synchronized void addDoctor(Doctor p){
        this.doctors.add(p);
        System.out.println("welcome doctor "+ p.getName());
    }
    public String addAppointment(Patient p, Doctor dr, String startTime){
        Slot slot  = getSlot(dr, getTime(startTime));
        if(slot==null){
            return "Invalid slot";
        }
        else if(slot.isBooked()){
            System.out.println("slot is not available, Patient will be added in the wait list");
            return "Added to wait list:"+manager.addWaitlingQueue(p,dr,slot);

        }
        return "Booked.Booking id:"+ manager.createAppointment(p, dr,slot);
    }
    public void removeAppointment(int id){
        manager.removeAppointment(id);
    }

    public synchronized void markDocAvailability(Doctor d1, String slotRequest) {
        String slots[] = slotRequest.split(",");
        List<Slot> slotList = new ArrayList<>();
        for(String slot  :slots){
            String[] sd = slot.split("-");
            LocalTime start  = getTime(sd[0]);
            LocalTime end = getTime(sd[1]);
            if(ChronoUnit.MINUTES.between(start, end)>30) {
                System.out.println("Sorry Dr. "+d1.getName()+" slots are 30 mins only");
                return;
            }
            slotList.add(new Slot(start, end)); 
        }
        d1.addSlots(slotList);
        System.out.println("done doc!");
        
    }


    public LocalTime getTime(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        // Parse the time string into a LocalTime object
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        return localTime;
    }

    public void showDoctors(Speciality speciality) {
       for(Doctor d : strategy.getDoctors(doctors, speciality.toString())){
        System.out.println(d);
       }
    }

    public Slot getSlot(Doctor d, LocalTime startTime){
        for(Slot s : d.getSlots()){
            if(s.getStartTime().equals(startTime)) return s;
        }
        return null;
    }
}
