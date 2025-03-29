package companySpecific.flipkart.wellnessCenter.managers;

import java.util.HashMap;
import java.util.Map;

import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;

public class SessionUserManager {
    public Map<String,Doctor> doctors = new HashMap<>();
    public Map<String,WellnessUser> users = new HashMap<>();
    

    public void addDoctor(Doctor dr){
        doctors.put(dr.getEmail(), dr);
    }
    public void addUser(WellnessUser user){
        users.put(user.getUserEmail(), user);
    }
    public void viewDoctors(){
        for(Doctor dr  : doctors.values()){
            System.out.println(dr);
        }
    }
    public void viewAvailableDr(){
        for(Doctor dr : doctors.values().stream().filter(d-> d.isDrAvailable()).toList()){
            System.out.println(dr);
        }
    }
    public void getUsers(){
        for(WellnessUser user : users.values()){
            System.out.println(user);
        }
    }
}
