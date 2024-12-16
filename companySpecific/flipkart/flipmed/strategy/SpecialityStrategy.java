package companySpecific.flipkart.flipmed.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import companySpecific.flipkart.flipmed.model.Doctor;

public class SpecialityStrategy implements EnquiryStrategy {

    @Override
    public List<Doctor> getDoctors(Set<Doctor> doctors,String search) {
       List<Doctor> availableDoctors = new ArrayList<>();
       for(Doctor d : doctors){
        if(d.getSpeciality().toString().equals(search)){
            availableDoctors.add(d);
        }
       }
       return availableDoctors;
    }
    
}
