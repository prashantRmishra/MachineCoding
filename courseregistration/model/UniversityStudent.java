package courseregistration.model;

import courseregistration.helper.UniversityIdGenerator;

public class UniversityStudent {
    public String name;
    public  String email;
    public String id;
    @Override
    public String toString() {
        return "UniversityStudent [name=" + name + ", email=" + email + ", id=" + id + "]";
    }
    String phone;
    String address;
    public UniversityStudent(String n, String email, String pno,String address){
        this.name= n;
        this.email = email;
        this.address = address;
        this.phone =pno;
        id = "student-"+UniversityIdGenerator.get();
    }
}
