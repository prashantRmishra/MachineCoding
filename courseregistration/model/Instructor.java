package courseregistration.model;

import courseregistration.helper.UniversityIdGenerator;

public class Instructor {
    public String name;
    public String phno;
    public String address;
    public String email;
    @Override
    public String toString() {
        return "Instructor [name=" + name + ", id=" + id + "]";
    }
    public String id;
    public Instructor(String name, String phno, String address ){
        this.name = name;
        this.phno = phno;
        this.address = address;
        this.id =  "Instructor-"+ UniversityIdGenerator.get();
    }
}
