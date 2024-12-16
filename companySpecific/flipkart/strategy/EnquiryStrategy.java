package companySpecific.flipkart.strategy;

import java.util.List;
import java.util.Set;


import companySpecific.flipkart.model.Doctor;

public interface EnquiryStrategy {
    public List<Doctor> getDoctors(Set<Doctor> doctors,String search);
}
