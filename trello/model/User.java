package trello.model;

public class User{
    private String userId;
    private String name;
    private String email;
    public User(String id, String name, String email){
        this.userId = id;
        this.name = name;
        this.email = email;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String toString(){
        String s = "user Id: "+userId+", name:"+name+", email:"+email;
        return s;
    }
}