package companySpecific.flipkart.manager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import companySpecific.flipkart.model.Appointment;
import companySpecific.flipkart.model.Doctor;
import companySpecific.flipkart.model.Patient;
import companySpecific.flipkart.model.Slot;
import companySpecific.flipkart.model.User;

public class AppointmentManager {
    private Map<Integer,Appointment> appointments;
    public AppointmentManager(){
        appointments = new HashMap<>();
    }

    public List<Appointment> getUserAppointments(User u){
        System.out.println(u);
        return appointments.values().stream().filter(a->{
             
            return a.getDoctor().getName().equals(u.getName()) || a.getPatient().getName().equals(u.getName());
        }).toList();
    }

    public synchronized int createAppointment(Patient p, Doctor d, Slot slot){
        //book the slot
        slot.bookSlot();
        //create appointment
        Appointment appointment  = new Appointment(slot, p, d);
        //add store appointmentDetails
        addAppointment(appointment);
        //return appointment Id
        return appointment.getId();
        
    }
    public synchronized void addAppointment(Appointment appointment){
        //update the user appointments
        appointments.put(appointment.getId(), appointment);
    }
    public synchronized boolean removeAppointment(int id){
        Appointment appointment =null;
        if(!appointments.containsKey(id)){
            System.out.println("given appointment does not exists!!");
            return false;
        }
        appointment = appointments.remove(id);
        if(!appointment.isWaitlist())
            appointment.getSlot().freeSlot();
        System.out.println("Booking cancelled");
        return true;
    }

    public synchronized int addWaitlingQueue(Patient p, Doctor dr, Slot slot) {
        //create appointment
        Appointment appointment  = new Appointment(slot, p, dr);
        appointment.setWaitlist();
        appointments.put(appointment.getId(), appointment);
        return appointment.getId();
    }
}
