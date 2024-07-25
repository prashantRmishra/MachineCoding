package machinecodingexamples.Splitwise;

public class User {
    public String userId;
    public String name;
    public String email;
    public String phone;


    public User(String n,String e, String p){
        this.email = e;
        this.name  = n;
        this.userId = email;
        this.phone = p;
    }
}
