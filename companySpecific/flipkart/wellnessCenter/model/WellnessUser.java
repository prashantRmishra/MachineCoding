package companySpecific.flipkart.wellnessCenter.model;

public class WellnessUser {
    private String name;
    private String email;
    
    @Override
    public String toString() {
        return "WellnessUser [name=" + name + ", email=" + email + "]";
    }
    public WellnessUser(String name, String email){
        this.name = name;
        this.email = email;
    }
    public String getUserName(){return this.name;}
    public String getUserEmail(){return this.email;}
}
