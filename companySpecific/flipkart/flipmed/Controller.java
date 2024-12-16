package companySpecific.flipkart.flipmed;

import java.util.List;

import companySpecific.flipkart.flipmed.manager.AppointmentManager;
import companySpecific.flipkart.flipmed.manager.UserManager;
import companySpecific.flipkart.flipmed.model.Appointment;
import companySpecific.flipkart.flipmed.model.Doctor;
import companySpecific.flipkart.flipmed.model.Patient;
import companySpecific.flipkart.flipmed.model.Slot;
import companySpecific.flipkart.flipmed.model.Speciality;
import companySpecific.flipkart.flipmed.model.User;

public class Controller {
    private UserManager userManager;
    private AppointmentManager appointmentManager;

    public Controller(UserManager userManager, AppointmentManager appointmentManager) {
        this.userManager = userManager;
        this.appointmentManager = appointmentManager;
    }

    //appointment related operation
    public List<Appointment> getAppointments(User u) {
        return appointmentManager.getUserAppointments(u);
    }

    public void addAppointment(Patient p, Doctor dr, String startTime) {
        Slot slot = dr.getSlot(Utility.getTime(startTime));
        if (slot == null) {
            print("Invalid slot");
        } else if (slot.isBooked()) {
            print("slot is not available, Patient will be added in the wait list");
            print("Added to wait list:" + appointmentManager.addToWaitListQueue(p, dr, slot));

        }
        else print("Booked.Booking id:" + appointmentManager.createAppointment(p, dr, slot));
    }

    public void removeAppointment(int id) {
        String msg = !appointmentManager.removeAppointment(id) ? "given appointment does not exists!!" :"Booking cancelled" ;
        print(msg);
    }


    ///user related operation
    public void addPatient(Patient p){
        String msg = userManager.addPatient(p) ?  p.getName() +" registered successfully!!" : "Patient already exists" ;
        print(msg);
    }
    public void addDoctor(Doctor d){
        String msg = userManager.addDoctor(d) ? "welcome doctor "+ d.getName() : "Doctor already exists";
        print(msg);
    }
    public void markDocAvailability(Doctor d, String time){
        String msg = userManager.markDocAvailability(d, time) ? "done doc!" : "Sorry Dr. "+d.getName()+" slots are 30 mins only";
        print(msg);
    }
    public void showDoctors(Speciality s){
        userManager.showAvailabilityDoctors(s);
    }

    private void print(String msg){
        System.out.println(msg);
    }

}
