package companySpecific.flipkart.flipmed.strategy;

import java.util.List;
import java.util.Set;

import companySpecific.flipkart.flipmed.model.Doctor;

public interface EnquiryStrategy {
    public List<Doctor> getDoctors(Set<Doctor> doctors,String search);
}
