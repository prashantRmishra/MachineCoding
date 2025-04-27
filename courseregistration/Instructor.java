package courseregistration;

public class Instructor {
    String name;
    String phno;
    String address;
    String email;
    @Override
    public String toString() {
        return "Instructor [name=" + name + ", id=" + id + "]";
    }
    String id;
    public Instructor(String name, String phno, String address ){
        this.name = name;
        this.phno = phno;
        this.address = address;
        this.id =  "Instructor-"+ UniversityIdGenerator.get();
    }
}
