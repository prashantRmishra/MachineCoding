package companySpecific.flipkart.manager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import companySpecific.flipkart.model.Appointment;
import companySpecific.flipkart.model.Doctor;
import companySpecific.flipkart.model.Patient;
import companySpecific.flipkart.model.Slot;
import companySpecific.flipkart.model.User;

public class AppointmentManager {
    private Map<User,List<Appointment>> userAppointments;

    public List<Appointment> getAppointment(User u){
        List<Appointment> appointments = userAppointments.get(u);
        if(appointments ==null) System.err.println("no appointment found");
        return appointments;
    }
    public synchronized int createAppointment(Patient p, Doctor d, Slot slot){
        //check if the given slot is free for the doctor
        if(slotAvailable(d.getSlots(),slot.getStartTime())){
            Appointment appointment  = new Appointment(slot, p.getName(), d.getName());
            //add appointment for both patient and doctor
            addAppointment(d, appointment);
            addAppointment(p, appointment);
            return appointment.getId();
        }
        return -1;
    }
    public boolean slotAvailable(Set<Slot> slots,LocalTime startTime){
        for(Slot s : slots){
            if(s.getStartTime().equals(startTime)) return !s.isBooked();
        }
        return false;
    }
    public void addAppointment(User u,Appointment appointment){
        List<Appointment> appointments = userAppointments.getOrDefault(u, new ArrayList<>());
        appointments.add(appointment);
        //update the user appointments
        userAppointments.put(u, appointments);
    }
    public boolean removeAppointment(User u, Appointment appointment){
        List<Appointment> appointments = userAppointments.get(u);
        if(appointment==null) {
            System.err.println("No appointment is present for the given user" + u.getName());
            return false;
        }
        int appointmentIndex = appointments.indexOf(appointment);
        if(appointmentIndex==-1){
            System.err.println("This appointment does not exists!!"+ appointment.getId());
            return false;
        }
        appointment.getSlot().freeSlot();
        appointments.remove(appointment);
        return true;
    }
}
