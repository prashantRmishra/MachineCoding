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
        userManager.addDoctor(d1);

        //create availability slots for the doc
        userManager.markDocAvailability(d1,"9:30-10:30");

        userManager.markDocAvailability(d1, "9:30-10:00,12:30-13:00,16:00-16:30");

        Doctor d2 = new Doctor("Dreadful", UserType.doctor,Speciality.dermatologist);
        userManager.addDoctor(d2);
        userManager.markDocAvailability(d2, "9:30-10:00,12:30-13:00,16:00-16:30");


        ///show availability by speciality 
        userManager.showDoctors(Speciality.cardiologist);


        //time for patient registration
        Patient p = new Patient("PatientA", UserType.patient);
        Patient p2 = new Patient("PatientB", UserType.patient);
        Patient p3 = new Patient("PatientC", UserType.patient);
        Patient p4 = new Patient("PatientD", UserType.patient);
        Patient p5 = new Patient("PatientF", UserType.patient);

        userManager.addPatient(p);
        userManager.addPatient(p2);
        userManager.addPatient(p3);
        userManager.addPatient(p4);
        userManager.addPatient(p5);

        //bookAppointment: (PatientA, Dr.Curious, 12:30)
        System.out.println(userManager.addAppointment(p, d1,"12:30"));
        userManager.showDoctors(Speciality.cardiologist);

        //cancel booking
        userManager.removeAppointment(1);
        userManager.showDoctors(Speciality.cardiologist);

        //bookAppointment: (PatientB, Dr.Curious, 12:30)
        System.out.println(userManager.addAppointment(p2, d1,"12:30"));

        //register new doc
        Doctor d3 = new Doctor("Daring", UserType.doctor,Speciality.dermatologist);
        userManager.addDoctor(d3);
        userManager.markDocAvailability(d3, "11:30-12:00,14:00-14:30");
        ///show availability by speciality 
        userManager.showDoctors(Speciality.dermatologist);

        //bookAppointment: (PatientF, Dr.Daring, 11:30)
        System.out.println(userManager.addAppointment(p5, d3,"11:30"));

         //bookAppointment: (PatientA, Dr.Curious, 12:30)
         System.out.println(userManager.addAppointment(p, d1,"12:30"));
         
         //bookAppointment: (PatientF, Dr.Curious, 09:30)
         System.out.println(userManager.addAppointment(p5, d1,"09:30"));
         
         //bookAppointment: (PatientC, Dr.Curious, 16:00)
         System.out.println(userManager.addAppointment(p2, d1,"16:00"));
         userManager.showDoctors(Speciality.cardiologist);
         //bookAppointment: (PatientC, Dr.Curious, 16:00)
         System.out.println(userManager.addAppointment(p3, d1,"16:00"));
         userManager.removeAppointment(7);

         System.out.println(userManager.getAppointments(p5));

    }
}

/*
 * 
 * welcome doctor curious
Sorry Dr. curious slots are 30 mins only
done doc!
welcome doctor Dreadful
done doc!
Dr.curious [slots=[Slot [09:30-10:00], Slot [16:00-16:30], Slot [12:30-13:00]]]
PatientA registered successfully!!
PatientB registered successfully!!
PatientC registered successfully!!
PatientD registered successfully!!
PatientF registered successfully!!
Booked.Booking id:1
Dr.curious [slots=[Slot [09:30-10:00], Slot [16:00-16:30]]]
Booking cancelled
Dr.curious [slots=[Slot [09:30-10:00], Slot [16:00-16:30], Slot [12:30-13:00]]]
Booked.Booking id:2
welcome doctor Daring
done doc!
Dr.Dreadful [slots=[Slot [12:30-13:00], Slot [16:00-16:30], Slot [09:30-10:00]]]
Dr.Daring [slots=[Slot [11:30-12:00], Slot [14:00-14:30]]]
Booked.Booking id:3
slot is not available, Patient will be added in the wait list
Added to wait list:4
Booked.Booking id:5
Booked.Booking id:6
Dr.curious [slots=No slots available]
slot is not available, Patient will be added in the wait list
Added to wait list:7
Booking cancelled
User [name=PatientF, type=patient]
[Appointment [id=3, slot=Slot [11:30-12:00], patient=User [name=PatientF, type=patient], doctor=Dr.Daring [slots=[Slot [14:00-14:30]]], isWaitlist=false], Appointment [id=5, slot=Slot [09:30-10:00], patient=User [name=PatientF, type=patient], doctor=Dr.curious [slots=No slots available], isWaitlist=false]]
prashantmishra@Prashants-MacBook-Air machinecodingexamples %
*/
