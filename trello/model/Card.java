package trello.model;

public class Card {
    private String id;
    private String name;
    private String description;
    private User assignedTo;

    public Card(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setDescription(String d){
        this.description = d;
    }
    public void updateDescription(String d){
        this.description = d;
    }
    public void setAssignedUser(User u){
        this.assignedTo = u;
    }
    public String getDescription(){
        return this.description;
    }
    public User getAssignedUser(){
        return this.assignedTo;
    }
    

  
    @Override
    public String toString(){
        String s = "id:"+id+", name:"+name+", description:"+description+", AssignedUser:"+(this.assignedTo ==null ? "no user assinged":assignedTo.getUserId());
        return s;
    }
    public void updateName(String name) {
        this.name  = name;
    }
    public void unassign() {
        this.assignedTo = null;
    }
}
