package companySpecific.flipkart.flipmed.manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.flipmed.model.Appointment;
import companySpecific.flipkart.flipmed.model.Doctor;
import companySpecific.flipkart.flipmed.model.Patient;
import companySpecific.flipkart.flipmed.model.Slot;
import companySpecific.flipkart.flipmed.model.User;

public class AppointmentManager {
    private Map<Integer,Appointment> appointments;

    public AppointmentManager(){
        appointments = new HashMap<>();
    }

    public List<Appointment> getUserAppointments(User u){
        
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
            System.out.println(id +" does not exists");
            return false;
        }
        appointment = appointments.remove(id);
        if(!appointment.isWaitlist()){
            appointment.getSlot().freeSlot();
            scheduleWaitListingAppointment(appointment);
        }  
        return true;
    }

    public synchronized int addToWaitListQueue(Patient p, Doctor dr, Slot slot) {
        //create appointment
        Appointment appointment  = new Appointment(slot, p, dr);
        appointment.setWaitlist();
        appointments.put(appointment.getId(), appointment);
        return appointment.getId();
    }

    private void scheduleWaitListingAppointment(Appointment removedAppointment){

        for(Map.Entry<Integer,Appointment> entry : appointments.entrySet()){
            Appointment existingAppointment = entry.getValue();
            if(existingAppointment.isWaitlist()&& existingAppointment.getDoctor().equals(removedAppointment.getDoctor()) && existingAppointment.getSlot().getStartTime().equals(removedAppointment.getSlot().getStartTime())){
                existingAppointment.freeWaitList();
                System.out.println("appointment "+existingAppointment.getId() +":has been scheduled");
                break;
            }
        }
    }
}
