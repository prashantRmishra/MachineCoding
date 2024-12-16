package companySpecific.flipkart;

import companySpecific.flipkart.manager.AppointmentManager;
import companySpecific.flipkart.manager.UserManager;
import companySpecific.flipkart.model.Doctor;
import companySpecific.flipkart.model.Patient;
import companySpecific.flipkart.model.UserType;
import companySpecific.flipkart.strategy.EnquiryStrategy;
import companySpecific.flipkart.strategy.SpecialityStrategy;

public class Driver {
    public static void main(String[] args) {
        AppointmentManager apponitmentManager = new AppointmentManager();
        //search strategy is Speciality
        EnquiryStrategy strategy = new SpecialityStrategy();
        UserManager userManager = new UserManager(apponitmentManager,strategy);
        Doctor d1 = new Doctor("curious", UserType.doctor,Speciality.cardiologist);
        userManager.createDoctor(d1);

        //create availability slots for the doc
        userManager.markDocAvailability(d1,"9:30-10:30");
        userManager.markDocAvailability(d1, "9:30-10:00,12:30-13:00,16:00-16:30");

        Doctor d2 = new Doctor("Dreadful", UserType.doctor,Speciality.dermatologist);
        userManager.createDoctor(d2);
        userManager.markDocAvailability(d2, "9:30-10:00,12:30-13:00,16:00-16:30");


        ///show availability by speciality 
        userManager.showDoctors(Speciality.cardiologist);


        //time for patient registration

        Patient p = new Patient("PatientA", UserType.patient);
        userManager.createPatient(p);
        //bookAppointment: (PatientA, Dr.Curious, 12:30)
        //userManager.addAppointment(p, null);

    }
}
