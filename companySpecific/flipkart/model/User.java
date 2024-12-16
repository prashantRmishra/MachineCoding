package companySpecific.flipkart.model;

public abstract class User {
    private String name;
    private UserType type;

    public User(String name,UserType t){
        this.name = name;
        this.type = t;
    }
    public String getName(){
        return this.name;
    }
    public UserType getType() {
        return type;
    }
}
