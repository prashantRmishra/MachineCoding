package Splitwise.model;


public class User {
    String userId;
    String name;
    String email;
    String phoneNumber;
    Debt debt = null;
    
    public User(String n, String e, String number){
        this.userId = e; //since email is unique, it can be used as userid
        this.name = n;
        this.email = e;
        this.phoneNumber = number;

        
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.userId;
    }
    public String getNumber(){
        return this.phoneNumber;
    }
    public String getEmail(){
        return this.email;
    }
    public void setDebt(Debt d){
        this.debt = d;
    }
    public Debt geDebt(){
        return this.debt;
    }
   
}
