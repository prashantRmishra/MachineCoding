package companySpecific.flipkart.flipmed;

import companySpecific.flipkart.flipmed.manager.AppointmentManager;
import companySpecific.flipkart.flipmed.manager.UserManager;
import companySpecific.flipkart.flipmed.model.Doctor;
import companySpecific.flipkart.flipmed.model.Patient;
import companySpecific.flipkart.flipmed.model.Speciality;
import companySpecific.flipkart.flipmed.model.UserType;
import companySpecific.flipkart.flipmed.strategy.EnquiryStrategy;
import companySpecific.flipkart.flipmed.strategy.SpecialityStrategy;

public class Driver {
    public static void main(String[] args) {
        AppointmentManager apponitmentManager = new AppointmentManager();
        //search strategy is Speciality
        EnquiryStrategy strategy = new SpecialityStrategy();
        UserManager userManager = new UserManager(strategy);
        Controller controller = new Controller(userManager, apponitmentManager);

        Doctor d1 = new Doctor("curious", UserType.doctor,Speciality.cardiologist);
        controller.addDoctor(d1);

        //create availability slots for the doc
        controller.markDocAvailability(d1,"9:30-10:30");

        controller.markDocAvailability(d1, "9:30-10:00,12:30-13:00,16:00-16:30");

        Doctor d2 = new Doctor("Dreadful", UserType.doctor,Speciality.dermatologist);
        controller.addDoctor(d2);
        controller.markDocAvailability(d2, "9:30-10:00,12:30-13:00,16:00-16:30");


        ///show availability by speciality 
        controller.showDoctors(Speciality.cardiologist);


        //time for patient registration
        Patient p = new Patient("PatientA", UserType.patient);
        Patient p2 = new Patient("PatientB", UserType.patient);
        Patient p3 = new Patient("PatientC", UserType.patient);
        Patient p4 = new Patient("PatientD", UserType.patient);
        Patient p5 = new Patient("PatientF", UserType.patient);

        controller.addPatient(p);
        controller.addPatient(p2);
        controller.addPatient(p3);
        controller.addPatient(p4);
        controller.addPatient(p5);

        //bookAppointment: (PatientA, Dr.Curious, 12:30)
        controller.addAppointment(p, d1,"12:30");
        userManager.showAvailabilityDoctors(Speciality.cardiologist);

        //cancel booking
        controller.removeAppointment(1);
        controller.showDoctors(Speciality.cardiologist);

        //bookAppointment: (PatientB, Dr.Curious, 12:30)
        controller.addAppointment(p2, d1,"12:30");

        //register new doc
        Doctor d3 = new Doctor("Daring", UserType.doctor,Speciality.dermatologist);
        controller.addDoctor(d3);
        controller.markDocAvailability(d3, "11:30-12:00,14:00-14:30");
        ///show availability by speciality 
        controller.showDoctors(Speciality.dermatologist);

        //bookAppointment: (PatientF, Dr.Daring, 11:30)
        controller.addAppointment(p5, d3,"11:30");

         //bookAppointment: (PatientA, Dr.Curious, 12:30)
         controller.addAppointment(p, d1,"12:30");
         
         //bookAppointment: (PatientF, Dr.Curious, 09:30)
         controller.addAppointment(p5, d1,"09:30");
         
         //bookAppointment: (PatientC, Dr.Curious, 16:00)
         controller.addAppointment(p2, d1,"16:00");
         userManager.showAvailabilityDoctors(Speciality.cardiologist);
         //bookAppointment: (PatientC, Dr.Curious, 16:00)
         controller.addAppointment(p3, d1,"16:00");
         controller.removeAppointment(6);

         System.out.println(controller.getAppointments(p5));

    }
}

/*
 * 
 * welcome doctor curious
Sorry Dr. curious slots are 30 mins only
done doc!
welcome doctor Dreadful
done doc!
Dr.curious [slots=[Slot [12:30-13:00], Slot [09:30-10:00], Slot [16:00-16:30]]]
PatientA registered successfully!!
PatientB registered successfully!!
PatientC registered successfully!!
PatientD registered successfully!!
PatientF registered successfully!!
Booked.Booking id:1
Dr.curious [slots=[Slot [09:30-10:00], Slot [16:00-16:30]]]
Booking cancelled
Dr.curious [slots=[Slot [12:30-13:00], Slot [09:30-10:00], Slot [16:00-16:30]]]
Booked.Booking id:2
welcome doctor Daring
done doc!
Dr.Daring [slots=[Slot [14:00-14:30], Slot [11:30-12:00]]]
Dr.Dreadful [slots=[Slot [12:30-13:00], Slot [16:00-16:30], Slot [09:30-10:00]]]
Booked.Booking id:3
slot is not available, Patient will be added in the wait list
Added to wait list:4
Booked.Booking id:5
Booked.Booking id:6
Dr.curious [slots=No slots available]
slot is not available, Patient will be added in the wait list
Added to wait list:7
appointment 7:has been scheduled
Booking cancelled
[
[slot=Slot [11:30-12:00],doc=Daring]
, 
[slot=Slot [09:30-10:00],doc=curious]
]
*/
